package com.zjp.test.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Producer {
    private static KafkaProducer<String, byte[]> kp;

    public  KafkaProducer<String, byte[]> getProducer() {
        if (kp == null) {
            Properties props = new Properties();
            props.put("bootstrap.servers", "10.10.10.16:9092");
            props.put("acks", "all");
            props.put("retries",0);
            props.put("batch.size", 16384);
            props.put("linger.ms", 1);
            props.put("buffer.memory", 33554432);
            props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
            kp = new KafkaProducer<String, byte[]>(props);
        }
        return kp;
    }

    public void getConsumer() {
        try {
            KafkaProducer<String, byte[]> producer = this.getProducer();
            File file = new File("D:\\系统环境变量.txt");
            byte[] buffer = null;
            Map<String, Object> map = new HashMap<>();
            try {
                FileInputStream fis = new FileInputStream(file);
                ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
                byte[] b = new byte[1000];
                int n;
                while ((n = fis.read(b)) != -1) {
                    bos.write(b, 0, n);
                }
                fis.close();
                bos.close();
                buffer = bos.toByteArray();
                long now = System.currentTimeMillis();
                ProducerRecord<String, byte[]> record = new ProducerRecord<String, byte[]>("dc-tbox-log", "测试"+now+".txt", buffer);
                producer.send(record);
                producer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] s) {
        Producer producer = new Producer();
        producer.getConsumer();;
    }
}
