package hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by akshay.mendole on 10/08/16.
 */
public class HbaseClientTTLTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        Configuration conf = new Configuration();

        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.set("hbase.zookeeper.quorum", "localhost");
        //conf.set("hbase.zookeeper.quorum", "prod-crm-exec-zk-0001,prod-crm-exec-zk-0002,prod-crm-exec-zk-0003");

        conf.set("zookeeper.znode.parent", "/hbase");

        Connection connection = ConnectionFactory.createConnection(conf);
        //Table table = connection.getTable(TableName.valueOf("w3.tg.wfe.EMDsetResult"));
        Table table = connection.getTable(TableName.valueOf("testtl"));
        byte[] colfamA = Bytes.toBytes("d");
        byte[] colHello = Bytes.toBytes("col");
        byte[] testvalue = Bytes.toBytes("valuee");

        Put put;
        for (int i = 0; i < 1; i++) {
            put = new Put(Bytes.toBytes("rowwneww"));
            put.setTTL(1000*30);
            put.addColumn(colfamA, colHello, testvalue);
            table.put(put);
        }

        //Thread.sleep(1000*50);

//        Get get = new Get(Bytes.toBytes("roww1"));
//        Result result = table.get(get);
//
//        Put put = resultToPut(new ImmutableBytesWritable(Bytes.toBytes("roww1")), result);
//        Delete delete = new Delete(Bytes.toBytes("roww1"));
//        table.delete(delete);
//        table.put(put);
//        Cell cell = result.getColumnLatestCell(colfamA, colHello);
//        Iterator<Tag> tagIterator = CellUtil.tagsIterator(cell.getTagsArray(), cell.getTagsOffset(), cell.getTagsLength());
//        while (tagIterator.hasNext()) {
//            Tag tag = tagIterator.next();
//            if (TagType.TTL_TAG_TYPE == tag.getType()) {
//                long ts = cell.getTimestamp();
//                assert tag.getTagLength() == Bytes.SIZEOF_LONG;
//                long ttl = Bytes.toLong(tag.getBuffer(), tag.getTagOffset(), tag.getTagLength());
//                System.out.println(ttl);
//            }
//        }

        System.out.println("done");
    }
    private static Put resultToPut(ImmutableBytesWritable key, Result result) throws IOException {
        Put put = new Put(key.get());
        for (KeyValue kv : result.raw()) {
            KeyValue keyValue = new KeyValue(kv.getRow(), kv.getFamily(), kv.getQualifier(), kv.getValue());
            put.add(keyValue);
            put.setTTL(1000*30);
        }
        return put;
    }

}
