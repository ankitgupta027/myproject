package hbase;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.UUID;

class Runner implements Runnable {

    Connection connection;
    private String longval ="";
    public Runner(Connection connection) {
        this.connection = connection;
        for (int i = 0; i < 1000000; i++) {
            longval = longval + "f";
        }
      //  System.out.println(longval);
    }
    byte[] colfamA = Bytes.toBytes("a");
    byte[] colB = Bytes.toBytes("b");
    byte[] colHello = Bytes.toBytes("Hello");
    byte[] testvalue = Bytes.toBytes("Hello how are you  i am fine thank you !!!!!!!!");


    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            Long start = System.currentTimeMillis();
            Put put = new Put(Bytes.toBytes(UUID.randomUUID().toString()));
            System.out.println("hello1");
            put.addColumn(colfamA, colfamA,testvalue);
            System.out.println("hello2");

            put.addColumn(colfamA, colB,Bytes.toBytes(longval));
            System.out.println("hello3");

            put.setTTL(1000*60*100);
            put.addColumn(colfamA, colHello, testvalue);
            System.out.println("hello4");

            Table table = null;
            try {
                table = connection.getTable(TableName.valueOf("testak5"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                table.put(put);
                System.out.println("hello5");

            } catch (IOException e) {
                e.printStackTrace();
            }
            Long end = System.currentTimeMillis();

            System.out.println("Took "+ (end-start) + " ms");
        }
    }
}

