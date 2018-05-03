package com.zjp.test.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class Application {

    Logger log = LoggerFactory.getLogger(Application.class);
    //定义了使用producerFactory和是否自动刷新，2个参数来构造kafka生产者模板类。1.2  发送kafka消息
    @Autowired
    KafkaTemplate<String, Object> producer;

    @RequestMapping("/")
    String hello(String aaa) {
        File file = new File("D:\\系统环境变量.txt");
        byte[] buffer = null;
        /*List<KafkaTemplate<String, String>> kms = new ArrayList<KafkaTemplate<String, String>>();
        KafkaTemplate<String, String> km = new KafkaTemplate<String, String>(topicName, entry);
        kms.add(km);*/
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
           /* map.put("buffer",buffer);
            map.put("fileName","file");*/
            //ProducerRecord<String, Object> data = new ProducerRecord<String, Object>("dc-bigdata",buffer);
            //producer.send(data);
            producer.send("dc-bigdata", "88", buffer);
           // producer.send("dc-bigdata", "2", JSON.toJSONBytes("messa"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "hello world";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
