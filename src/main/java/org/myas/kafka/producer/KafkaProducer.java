package org.myas.kafka.producer;

import java.io.Closeable;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

/**
 * @author Mykhailo Yashchuk
 */
public interface KafkaProducer<KeyType, ValType> extends Closeable {
    void send(ValType value);

    void send(String topic, ValType value);

    void send(String topic, KeyType key, ValType value);

    Future<RecordMetadata> sendAsync(ValType value);

    Future<RecordMetadata> sendAsync(String topic, ValType value);

    Future<RecordMetadata> sendAsync(String topic, KeyType key, ValType value);

    Future<RecordMetadata> sendAsync(ValType value, Callback callback);

    Future<RecordMetadata> sendAsync(String topic, ValType value, Callback callback);

    Future<RecordMetadata> sendAsync(String topic, KeyType key, ValType value, Callback callback);
}
