package hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import java.io.IOException;
import java.util.List;

/**
 * Created by akshay.mendole on 27/09/16.
 */
public class HBaseRegionMerger {
    private static Connection connection;

    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.set("hbase.zookeeper.quorum", "prod-crm-exec-zk-0001,prod-crm-exec-zk-0002,prod-crm-exec-zk-0003");
       // conf.set("hbase.zookeeper.quorum", "qa-ceryx-hbaseds-429560");
        conf.set("zookeeper.znode.parent", "/hbase-unsecure");
        connection = ConnectionFactory.createConnection(conf);
        List<HRegionLocation> hRegionLocations = connection.getRegionLocator(TableName.valueOf("w3.tg.wfe.EMDsetResult")).getAllRegionLocations();

        for (int i = 0; i < hRegionLocations.size(); i++) {
            System.out.println(hRegionLocations.get(i).getRegionInfo().getEncodedName());
        }
    }

//    private long getRegionSize(HRegionInfo hri) {
//        ServerName sn = connection.getRegionLocator("").ge
//        RegionLoad regionLoad = masterServices.getServerManager().getLoad(sn).
//                getRegionsLoad().get(hri.getRegionName());
//        if (regionLoad == null) {
//            LOG.debug(hri.getRegionNameAsString() + " was not found in RegionsLoad");
//            return -1;
//        }
//        return regionLoad.getStorefileSizeMB();
//    }

}
