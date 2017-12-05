package org.myas.kafka.producer;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * @author Mykhailo Yashchuk
 */
public class BasicKafkaProducer implements KafkaProducer {
    private org.apache.kafka.clients.producer.KafkaProducer<byte[], byte[]> producer;
    private String topic;

    public BasicKafkaProducer(Properties properties, String topic) {
        this.producer = new org.apache.kafka.clients.producer.KafkaProducer<>(Objects.requireNonNull(properties));
        this.topic = topic;
    }

    @Override
    public void send(byte[] bytes) {
        send(topic, bytes);
    }

    @Override
    public void send(String topic, byte[] bytes) {
        Objects.requireNonNull(topic, "Topic to send cannot be null");
        producer.send(new ProducerRecord<>(topic, bytes));
    }

    @Override
    public void close() throws IOException {
        producer.close();
    }
}
