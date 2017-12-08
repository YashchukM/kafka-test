package org.myas.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.function.Consumer;

/**
 * @author Mykhailo Yashchuk
 */
public interface KafkaConsumer<KeyType, ValType> {
    void init();

    void start(Consumer<ConsumerRecord<KeyType, ValType>> recordConsumer);

    void stop();

    void pause();

    void resume();
}
