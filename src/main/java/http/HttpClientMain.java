package http;

import org.apache.htrace.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
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
import java.util.*;
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


        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(13, 13, 0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue(1000),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        String[] files = {"whatsapp.list"};

        for (int i = 0; i < files.length; i++ ) {
            String fileName = "/var/tmp/"+files[i];
            System.out.println("Reading file : " + fileName);
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
        }

        Thread.sleep(1000 * 60 * 60);
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

            HttpPut httpPut = new HttpPut("http://10.47.4.150/v1/registration/wa/flipkart");
            try {
                httpPut.setHeader("x-api-key", "BM9kig6BGwN68aeJG2ESWkxt8");
                httpPut.setHeader("Content-Type", "application/json");
                StringEntity entity = new StringEntity("{\"user_identifier\": \"" + deviceId + "\"}");
                httpPut.setEntity(entity);
                CloseableHttpResponse responseUserSvc = httpClient.execute(httpPut, context);
                if (responseUserSvc.getStatusLine().getStatusCode() == 202) {
                    System.out.println("success:" + deviceId);
                } else {
                    System.out.println("failed:" + deviceId + " status " + responseUserSvc.getStatusLine().getStatusCode());
                }
            } catch (Exception e) {
                System.out.println("User Service update failed : " + e.getMessage() + e.getCause());
            } finally {
                httpPut.releaseConnection();
            }
        }
    }
}