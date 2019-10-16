package com.github.sougat818.kafka.tutorial1;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.ExecutionException;


public class ProducerDemoKeys {

    private final static Logger logger = LoggerFactory.getLogger(ProducerDemoKeys.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // create Producer properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //create the producer

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        for (int i = 0; i < 10; i++) {
            // create producer record
            ProducerRecord<String, String> record = new ProducerRecord<>("first_topic", "key_" + i, "hello world " + i);

            String message = "hello world " + i;

            //send data
            producer.send(record, (recordMetadata, e) -> {
                if (e != null) {
                    logger.error("Exception while sending message", e);
                } else {
                    logger.info("Message {} sent to Offset: {} Partition {} Topic {} TimeStamp {}", message , recordMetadata.offset(), recordMetadata.partition(), recordMetadata.topic(), recordMetadata.timestamp());
                }
            }).get();
        }

        //flush and close producer
        producer.flush();
        producer.close();
    }
}
