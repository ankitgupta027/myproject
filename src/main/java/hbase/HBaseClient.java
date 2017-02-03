package hbase;

import javafx.scene.control.Tab;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Syncable;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by akshay.mendole on 10/08/16.
 */
public class HBaseClient {
    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();


        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.set("hbase.zookeeper.quorum", "localhost");
       // conf.set("hbase.zookeeper.quorum", "uss-hbase-prod-zk-6024478,uss-hbase-prod-zk-6024479,uss-hbase-prod-zk-6024477");
        //conf.set("hbase.zookeeper.quorum", "prod-crm-exec-zk-0001,prod-crm-exec-zk-0002,prod-crm-exec-zk-0003");
        conf.set("zookeeper.znode.parent", "/hbase-unsecure");

        Connection connection = ConnectionFactory.createConnection(conf);

        Put put = new Put(Bytes.toBytes("testtl"));
        Table table = connection.getTable(TableName.valueOf("testtl"));
        table.put(put);


     //   byte[] longstring = Bytes.toBytes("Hello how are you  i am fine thank you !!!!!!!!Hello how are you  i am fine thank you !!!!!!!!Hello how are you  i am fine thank you !!!!!!!!Hello how are you  i am fine thank you !!!!!!!!Hello how are you  i am fine thank you !!!!!!!!Hello how are you  i am fine thank you !!!!!!!!Hello how are you  i am fine thank you !!!!!!!!Hello how are you  i am fine thank you !!!!!!!!Hello how are you  i am fine thank you !!!!!!!!Hello how are you  i am fine thank you !!!!!!!!Hello how are you  i am fine thank you !!!!!!!!Hello how are you  i am fine thank you !!!!!!!!Hello how are you  i am fine thank you !!!!!!!!Hello how are you  i am fine thank you !!!!!!!!Hello how are you  i am fine thank you !!!!!!!!Hello how are you  i am fine thank you !!!!!!!!Hello how are you  i am fine thank you !!!!!!!!Hello how are you  i am fine thank you !!!!!!!!Hello how are you  i am fine thank you !!!!!!!!Hello how are you  i am fine thank you !!!!!!!!Hello how are you  i am fine thank you !!!!!!!!Hello how are you  i am fine thank you !!!!!!!!");

    //    Scan scan = new Scan(Bytes.toBytes("FKRetailCustomer:002f:b3bbaff5654348d1:EM-MPNAH3IQW8Y7Z20:"),Bytes.toBytes("FKRetailCustomer:002f:b3bbaff5654348d1:EM-MPNAH3IQW8Y7Z20:"));
//      //  Scan scan = new Scan(Bytes.toBytes("FKRetailCustomer:002f:b3bbaff5654348d1:EM-MPNAH3IQW8Y7Z20:"),Bytes.toBytes("FKRetailCustomer:0030:ACC22EF7F322FB749A0AFF6BF7F5F9A06AE:EM-WEBC68E883255D0475:"));
//     //   Scan scan = new Scan(null,Bytes.toBytes("FKRetailCustomer:0030:ACC22EF7F322FB749A0AFF6BF7F5F9A06AE:EM-WEBC68E883255D0475:"));
//       // Scan scan = new Scan(Bytes.toBytes(""),Bytes.toBytes("FKRetailCustomer:0030:ACC22EF7F322FB749A0AFF6BF7F5F9A06AE:EM-WEBC68E883255D0475:"));
//      //  Scan scan = new Scan(Bytes.toBytes(""),Bytes.toBytes(""));
//        scan.setCaching(100);
//      //  Scan scan = new Scan();
//        scan.setCacheBlocks(false);
//        ResultScanner resultScanner=table.getScanner(scan);
//        Result result = resultScanner.next();
//
//        do {
//            System.out.println(Bytes.toString(result.getRow()));
//            result = resultScanner.next();
//        } while (result != null);


//        ExecutorService executorService = Executors.newFixedThreadPool(100);
//
//        for (int i = 0; i < 1; i++) {
//            Runner runner = new Runner(connection);
//            executorService.submit(runner);
//        }
//        System.out.println("done");
    }

}
