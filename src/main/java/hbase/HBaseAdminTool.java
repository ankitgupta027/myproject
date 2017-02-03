package hbase;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.Durability;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * Created by akshay.mendole on 09/09/16.
 */
public class HBaseAdminTool {
    public static void main(String[] args) {
        Configuration conf = new Configuration();

        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.set("hbase.zookeeper.quorum", "localhost");
        conf.set("zookeeper.znode.parent", "/hbase");
        try {
            HBaseAdmin admin = new HBaseAdmin(conf);
            HTableDescriptor hTableDescriptor = new HTableDescriptor(Bytes.toBytes("device_info"));


            hTableDescriptor.setDurability(Durability.ASYNC_WAL);
            HColumnDescriptor family = new HColumnDescriptor(Bytes.toBytes("d"));

            hTableDescriptor.setMaxFileSize(2147483648l);
            hTableDescriptor.addFamily(family);
            admin.modifyTable(Bytes.toBytes("device_info"), hTableDescriptor);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
