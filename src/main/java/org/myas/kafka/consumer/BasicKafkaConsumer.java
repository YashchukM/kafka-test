package org.myas.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author Mykhailo Yashchuk
 */
public class BasicKafkaConsumer<KeyType, ValType> implements KafkaConsumer<KeyType, ValType> {
    private static final int DEFAULT_POLL_TIMEOUT = 100;

    private org.apache.kafka.clients.consumer.KafkaConsumer<KeyType, ValType> consumer;
    private String topic;
    private int pollTimeout = DEFAULT_POLL_TIMEOUT;

    public BasicKafkaConsumer(Properties properties, String topic,
                              Deserializer<KeyType> keyDeserializer, Deserializer<ValType> valDeserializer) {
        this.topic = Objects.requireNonNull(topic);
        this.consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<>(
                Objects.requireNonNull(properties), keyDeserializer, valDeserializer);
    }

    @Override
    public void init() {
        consumer.subscribe(Collections.singletonList(topic));
    }

    @Override
    public void start(Consumer<ConsumerRecord<KeyType, ValType>> recordConsumer) {
        try {
            while (true) {
                ConsumerRecords<KeyType, ValType> records = consumer.poll(pollTimeout);
                for (ConsumerRecord<KeyType, ValType> record : records) {
                    recordConsumer.accept(record);
                }
                consumer.commitAsync();
            }
        } catch (WakeupException e) {
            // Ignoring as we quit
        } finally {
            try {
                consumer.commitSync();
            } finally {
                consumer.close();
            }
        }
    }

    @Override
    public void stop() {
        consumer.wakeup();
    }

    @Override
    public void pause() {
        consumer.pause(allPartitions());
    }

    @Override
    public void resume() {
        consumer.resume(allPartitions());
    }

    private List<TopicPartition> allPartitions() {
        return consumer.partitionsFor(topic).stream()
                .map(partitionInfo -> new TopicPartition(partitionInfo.topic(), partitionInfo.partition()))
                .collect(Collectors.toList());
    }

    public int getPollTimeout() {
        return pollTimeout;
    }

    public void setPollTimeout(int pollTimeout) {
        this.pollTimeout = pollTimeout;
    }
}
