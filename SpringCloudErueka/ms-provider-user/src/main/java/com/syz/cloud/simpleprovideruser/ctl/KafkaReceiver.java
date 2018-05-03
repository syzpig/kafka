package com.syz.cloud.simpleprovideruser.ctl;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import com.syz.cloud.simpleprovideruser.entity.User;
import com.syz.cloud.simpleprovideruser.service.Sink;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@EnableBinding(Sink.class)
public class KafkaReceiver {

    private final Logger logger = LoggerFactory.getLogger(KafkaReceiver.class);
   //@Payload注解内容为消息体，@Headers注解获取所有的Header头信息，@Header注解获取指定name的头信息。
   //获取实体方法一
    /* @StreamListener(Sink.INPUT_1)
    private void receive(@Payload User vote) {
         System.out.println("============="+vote.getAge());
        logger.info("receive message :=========== " + vote.getName());
    }*/
   //获取实体方法二
  /*@StreamListener(Sink.INPUT_1)
    private void receive(Message<User> message) {
      User obj = message.getPayload();
      System.out.println("============="+obj);
      logger.info("receive message :=========== " + obj.getName());
    }*/
   //获取集合方法
   /* @StreamListener(Sink.INPUT_1)
    private void receive(List<User> message) {
        //byte[] obj = message.getPayload();
        System.out.println(message);
        //logger.info("receive message :=========== " + obj.getName());
    }*/
   @StreamListener(Sink.INPUT_1)
    private void receive(Map message) {
        //byte[] obj = message.getPayload();
        System.out.println(message);
        //logger.info("receive message :=========== " + obj.getName());
    }

  //上传图片
    //@StreamListener(Sink.INPUT_1)
    private void receive(byte[] message) {
        //byte[] obj = message.getPayload();
       // byte[] bytes = message.getBytes();
        //System.out.println("====="+bytes);
        InputStream sbs = new ByteArrayInputStream(message);
        //设置cos容器中文件路径
        //String key = fileLog.getOriginalFilename();
        //上传到腾讯云
        PutObjectResult putObjectResult = null;
        try {
            //InputStream fileInputStream = fileLog.getInputStream();
            ObjectMetadata objectMetadata = new ObjectMetadata();
            // 设置输入流长度为 500
            objectMetadata.setContentLength(sbs.available());
            // 设置 Content type, 默认是 application/octet-stream
            objectMetadata.setContentType("charset=utf-8");
            objectMetadata.setContentEncoding("utf-8");
            putObjectResult = this.getCOSClient().putObject("myfiles-1256406754", "4.txt", sbs, objectMetadata);
        } catch (CosServiceException cs) {
            //logger.info(fileName + "调用上传文件到腾讯云COS失败！");
            cs.printStackTrace();
        } catch (CosClientException cc) {
            cc.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.getCOSClient().shutdown();
        //logger.info("receive message :=========== " + obj.getName());
    }
    /**
     * 获取腾讯云cos客户端
     */
    public COSClient getCOSClient() {
        // 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials("AKID7A2CQX2eTL7iDiCPbdUWnyypaBbtk4Ut", "SkrxwkMo8HzV0KTaWL4fBWghZGza6Ugj");
        // 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region("ap-shanghai"));
        // 生成cos客户端
        return new COSClient(cred, clientConfig);
    }
}