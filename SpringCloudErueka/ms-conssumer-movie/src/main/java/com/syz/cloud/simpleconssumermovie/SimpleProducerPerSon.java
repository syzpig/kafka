package com.syz.cloud.simpleconssumermovie;

import com.syz.cloud.simpleconssumermovie.entity.PerSon;
import org.apache.kafka.clients.producer.Callback;
//import KafkaProducer packages
import org.apache.kafka.clients.producer.KafkaProducer;

import org.apache.kafka.clients.producer.Producer;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class SimpleProducerPerSon {
    public static void main(String[] args) throws Exception {

        //Assign topicName to string variable
        String topicName = "mypartition001";
        // create instance for properties to access producer configs
        Properties props = new Properties();
        //Assign localhost id
        props.put("bootstrap.servers", "localhost:9092");
        //Set acknowledgements for producer requests.
        props.put("acks", "all");
        //If the request fails, the producer can automatically retry,
        props.put("retries", 0);
        props.put("metadata.fetch.timeout.ms", 30000);
        //contorller the send method ：sync or async default ： sync
        //Specify buffer size in config
        props.put("batch.size", 16384);
        //Reduce the no of requests less than 0
        props.put("linger.ms", 1);
        //The buffer.memory controls the total amount of memory available to the producer for buffering.
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "com.syz.cloud.simpleconssumermovie.utils.EncodeingKafka");
//        props.put("partitioner.class", "继承了Partition的类，实现的是根据指定的算法把消息推送到指定的分区中com.ys.test.SpringBoot.zktest.util.MyPartition");
        Producer<String, Object> producer = new KafkaProducer<String, Object>(props);
        long startTimes = System.currentTimeMillis();
        System.out.println();

        for (int i = 0; i < 2; i++) {

            final int index = i;
            PerSon perSon = new PerSon();
            perSon.setAge(i);
            perSon.setAddr("My Producer TEST001" + i);
            perSon.setName("MyTest " + i);
           /* IDCard card = new IDCard();
            card.setCardName("MyTest"+i+"'s idCard");
            card.setCardid(10000000000L);
            perSon.setCard(card);*/

            List<PerSon> asList = Arrays.asList(perSon, perSon);
//            producer.send(new ProducerRecord<String, Object>(topicName,Integer.toString(i),asList));
//            producer.send(new ProducerRecord<String, Object>(topicName, Integer.toString(i), perSon));
            producer.send(new ProducerRecord<String, Object>(topicName, Integer.toString(i), asList), new Callback() {

                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if (metadata != null) {
                        System.out.println(index + "  发送成功：" + "checksum: " + " offset: " + metadata.offset() + " partition: " + metadata.partition() + " topic: " + metadata.topic());
                    }
                    if (exception != null) {
                        System.out.println(index + "异常：" + exception.getMessage());
                    }
                }
            });
        }
        producer.close();
    }
}
