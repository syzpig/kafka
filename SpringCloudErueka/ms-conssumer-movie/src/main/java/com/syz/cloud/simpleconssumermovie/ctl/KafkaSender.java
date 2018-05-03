package com.syz.cloud.simpleconssumermovie.ctl;


import com.syz.cloud.simpleconssumermovie.entity.User;
import com.syz.cloud.simpleconssumermovie.service.Source;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;

import java.io.*;
import java.util.*;

@EnableBinding(Source.class)
public class KafkaSender {

    private final Logger logger = LoggerFactory.getLogger(KafkaSender.class);

    @Autowired
    private Source source;

    public void sendMessage() {
        User user = new User();
        List<User> users=new ArrayList<>();
        users.add(user);
        Map<String,Object> map = new HashMap<>();
        map.put("user",user);

        byte[] aa = new byte[]{1, 2, 3};
        String s = new String(aa);
        try {
            source.output1().send(MessageBuilder.withPayload(user).build());
        } catch (Exception e) {
            logger.info("消息发送失败，原因：" + e);
            e.printStackTrace();
        }
        //上传图片
        String runtime = new Date().toString();

        File file = new File("D:\\系统环境变量.txt");
        byte[] buffer = null;
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
            //source.output1().send(MessageBuilder.withPayload(buffer).build());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
