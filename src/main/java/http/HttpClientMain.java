package http;

import org.apache.htrace.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * An example that performs GETs from multiple threads.
 *
 */
class HttpClientMain {

    public static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws Exception {
        // Create an HttpClient with the ThreadSafeClientConnManager.
        // This connection manager must be used if more than one thread will
        // be using the HttpClient.
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(50);
        cm.setDefaultMaxPerRoute(50);

        CloseableHttpClient httpclient = HttpClients.custom()
                .setConnectionManager(cm)
                .build();


        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(12, 12, 0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue(12),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        String fileName = "/var/tmp/ids.txt";
        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);
            String sCurrentLine;
            br = new BufferedReader(new FileReader(fileName));

            while ((sCurrentLine = br.readLine()) != null) {
                threadPoolExecutor.submit(new Worker(httpclient,sCurrentLine));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        Thread.sleep(1000*60*60);
    }

    /**
     * A thread that performs a GET.
     */
    private static class Worker implements Runnable {

        private final CloseableHttpClient httpClient;
        private final HttpContext context;
        private final String deviceId;

        Worker(CloseableHttpClient httpClient, String deviceId) {
            this.httpClient = httpClient;
            this.context = new BasicHttpContext();
            this.deviceId = deviceId;
        }

        @Override
        public void run() {
            try {
                HttpGet httpget = new HttpGet("http://10.33.109.245:8000/v1/apps/retail/devices/" + deviceId);
                CloseableHttpResponse response = httpClient.execute(httpget, context);
                CloseableHttpResponse responseConnekt = null;
                try {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        byte[] bytes = EntityUtils.toByteArray(entity);
                        if (response.getStatusLine().getStatusCode() != 200) {
                            System.out.println("non 200 for " + deviceId);
                            return;
                        }

                        Map<String, Object> responseMap = objectMapper.readValue(bytes, HashMap.class);
                        System.out.println("deviceService response is " + ((Map<String, String>) responseMap.get("data")).get("appVersion"));

                        String appVersion = ((Map<String, String>) responseMap.get("data")).get("appVersion");
                        HttpPatch httpPatch = new HttpPatch("http://10.47.0.120/v1/registration/push/unknown/RetailApp/" + deviceId);
                        httpPatch.setHeader("x-api-key", "b0979afd-2ce3-4786-af62-ab53f88204ff");
                        httpPatch.setHeader("content-type", "application/json");
                        StringEntity entity1 = new StringEntity("{\"appVerison\": \"" + appVersion + "\"}");
                        httpPatch.setEntity(entity1);
                        responseConnekt = httpClient.execute(httpget, context);
                        if (responseConnekt.getStatusLine().getStatusCode() == 200) {
                            System.out.println("success:" + deviceId);
                        } else {
                            System.out.println("failes:" + deviceId + " status " + responseConnekt.getStatusLine().getStatusCode());
                        }

                    }
                } finally {
                    if (response != null) {
                        response.close();
                    }
                    if (responseConnekt!=null) {
                        responseConnekt.close();
                    }
                }
            } catch (Exception e) {
                System.out.println("error: " + Arrays.toString(e.getStackTrace()));
            }
        }

    }

}