package com.zjp.test.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@EnableKafka
public class Listener {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());


	//4 就是需要创建一个Listener类
    //@KafkaListener(topics = {"dc-bigdata"})
    public void listen(ConsumerRecord<?, ?> record) {
    	System.out.println(record.key());
        logger.info("kafka的key: " + record.key());
        logger.info("kafka的value: " + record.value().toString());
    }
}
