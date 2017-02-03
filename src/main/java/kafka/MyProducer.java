package kafka;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * Created by akshay.mendole on 10/10/16.
 */
public class MyProducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "10.33.93.33:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 0);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        System.out.println("starting");
        Producer<String, String> myProducer = new KafkaProducer<>(props);
        for(int i = 0; i < 100; i++)
            myProducer.send(new ProducerRecord("mytopic", Integer.toString(i), Integer.toString(i)));

        myProducer.close();
        System.out.println("done");
    }
}
