package org.myas.kafka.producer;

import java.io.Closeable;

/**
 * @author Mykhailo Yashchuk
 */
public interface KafkaProducer extends Closeable {
    void send(byte[] bytes);

    void send(String topic, byte[] bytes);
}
