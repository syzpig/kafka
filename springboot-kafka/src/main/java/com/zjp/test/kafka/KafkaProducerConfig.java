package com.zjp.test.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
@EnableKafka
public class KafkaProducerConfig {
	 	@Value("10.10.10.16:9092")
	    private String servers;
	    @Value("${kafka.producer.retries}")
	    private int retries;
	    @Value("${kafka.producer.batch.size}")
	    private int batchSize;
	    @Value("${kafka.producer.linger}")
	    private int linger;
	    @Value("${kafka.producer.buffer.memory}")
	    private int bufferMemory;


	    public Map<String, Object> producerConfigs() {
	        Map<String, Object> props = new HashMap<String, Object>();
	        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
	        props.put(ProducerConfig.RETRIES_CONFIG, retries);
	        props.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
	        props.put(ProducerConfig.LINGER_MS_CONFIG, linger);
	       // props.put("acks", "all");//这个配置意味着leader会等待所有的follower同步完成。这个确保消息不会丢失，除非kafka集群中所有机器挂掉。这是最强的可用性保证。
	        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);
	        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
	        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArraySerializer");
	        return props;
	    }

	    public ProducerFactory<String, Object> producerFactory() {
	        return new DefaultKafkaProducerFactory<String, Object>(producerConfigs());
	    }

	    @Bean
	    public KafkaTemplate<String, Object> kafkaTemplate() {
	        return new KafkaTemplate<String, Object>(producerFactory());
	    }
}
