package com.celada.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ExampleProducer {

    private static final Logger log = LoggerFactory.getLogger(ExampleProducer.class);

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "1");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 1000000; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<>("first-topic", String.valueOf(i), "message");
            producer.send(record);
        }

        producer.flush();
        log.info("Processing time = {} ms", (System.currentTimeMillis() - startTime));
        producer.close();
    }
}
