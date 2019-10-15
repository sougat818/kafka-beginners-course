package com.github.sougat818.kafka.tutorial1;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;


public class ProducerDemoWithDemo {

    private final static Logger logger = LoggerFactory.getLogger(ProducerDemoWithDemo.class);

    public static void main(String[] args) {
        // create Producer properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //create the producer

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        for (int i = 0; i < 10; i++) {
            // create producer record
            ProducerRecord<String, String> record = new ProducerRecord<>("first_topic", "hello world " + i);
            logger.info("test");
            //send data
            producer.send(record, (recordMetadata, e) -> {
                if (e != null) {
                    logger.error("Exception while sending message", e);
                } else {
                    logger.info("Message sent to Offset: {} Partition {} Topic {} TimeStamp {}", recordMetadata.offset(), recordMetadata.partition(), recordMetadata.topic(), recordMetadata.timestamp());
                }
            });
        }

        //flush and close producer
        producer.flush();
        producer.close();
    }
}
