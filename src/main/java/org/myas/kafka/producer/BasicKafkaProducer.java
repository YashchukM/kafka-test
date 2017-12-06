package org.myas.kafka.producer;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.xml.crypto.dsig.keyinfo.KeyValue;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.Serializer;

/**
 * @author Mykhailo Yashchuk
 */
public class BasicKafkaProducer<KeyType, ValType> implements KafkaProducer<KeyType, ValType> {
    private org.apache.kafka.clients.producer.KafkaProducer<KeyType, ValType> producer;
    private String topic;

    public BasicKafkaProducer(Properties properties, String topic, Serializer<KeyType> keySerializer, Serializer<ValType> valSerializer) {
        this.producer = new org.apache.kafka.clients.producer.KafkaProducer<>(
                Objects.requireNonNull(properties), keySerializer, valSerializer);
        this.topic = topic;
    }

    @Override
    public void send(ValType value) {
        send(topic, value);
    }

    @Override
    public void send(String topic, ValType value) {
        try {
            Objects.requireNonNull(topic, "Topic to send cannot be null");
            producer.send(new ProducerRecord<>(topic, value)).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void send(String topic, KeyType key, ValType value) {
        try {
            Objects.requireNonNull(topic, "Topic to send cannot be null");
            producer.send(new ProducerRecord<>(topic, key, value)).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Future<RecordMetadata> sendAsync(ValType value) {
        return sendAsync(topic, value);
    }

    @Override
    public Future<RecordMetadata> sendAsync(String topic, ValType value) {
        Objects.requireNonNull(topic, "Topic to send cannot be null");
        return producer.send(new ProducerRecord<>(topic, value));
    }

    @Override
    public Future<RecordMetadata> sendAsync(String topic, KeyType key, ValType value) {
        Objects.requireNonNull(topic, "Topic to send cannot be null");
        return producer.send(new ProducerRecord<>(topic, key, value));
    }

    @Override
    public Future<RecordMetadata> sendAsync(ValType value, Callback callback) {
        return null;
    }

    @Override
    public Future<RecordMetadata> sendAsync(String topic, ValType value, Callback callback) {
        return null;
    }

    @Override
    public Future<RecordMetadata> sendAsync(String topic, KeyType key, ValType value, Callback callback) {
        Objects.requireNonNull(topic, "Topic to send cannot be null");
        return producer.send(new ProducerRecord<>(topic, key, value), callback);
    }

    @Override
    public void close() throws IOException {
        producer.close();
    }
}
