package com.syz.cloud.simpleconssumermovie;

import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

public class KafkaListenerTest {

    public static void main(String[] args) throws Exception {
        KafkaListenerTest kafkaListenerTest = new KafkaListenerTest();
        kafkaListenerTest.consumerConfigs();
    }

    @Bean
    public Map consumerConfigs() {
        Map props = new HashMap();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        String groupId = "api_" + RandomStringUtils.randomAlphanumeric(4);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        return props;
    }
    @Bean
    public ConsumerFactory consumerFactory() {
        return new DefaultKafkaConsumerFactory(consumerConfigs());
    }
    @Bean
    public KafkaListenerContainerFactory  kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(3);
        factory.getContainerProperties().setPollTimeout(3000);
        return factory;
    }
    public class KafkaListenerPractices {
        @KafkaListener(topics = "newtest001", containerFactory = "kafkaListenerContainerFactory")
        public void listener(ConsumerRecord record) {
            System.out.println(record+"==============");
        }
    }
    @Bean
    public KafkaListenerPractices kafkaListenerPractices() {
        return new KafkaListenerPractices();
    }

}
