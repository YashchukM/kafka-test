package org.myas.kafka.consumer;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.TopicPartition;

/**
 * @author Mykhailo Yashchuk
 */
public class BasicKafkaConsumer<KeyType, ValType> implements KafkaConsumer {
    private org.apache.kafka.clients.consumer.KafkaConsumer<KeyType, ValType> consumer;
    private String topic;
    private int pollTimeout;

    public BasicKafkaConsumer(String topic, Properties properties) {
        this.topic = Objects.requireNonNull(topic);
        this.consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<>(Objects.requireNonNull(properties));
    }

    public void init() {
        consumer.subscribe(Collections.singletonList(topic));
    }

    // TODO: commit
    public void start(Consumer<ConsumerRecord<KeyType, ValType>> recordConsumer) {
        try {
            while (true) {
                ConsumerRecords<KeyType, ValType> records = consumer.poll(pollTimeout);
                for (ConsumerRecord<KeyType, ValType> record : records) {
                    recordConsumer.accept(record);
                }
            }
        } finally {
            consumer.close();
        }
    }

    public void stop() {
        consumer.wakeup();
    }

    public void pause() {
        consumer.pause(allPartitions());
    }

    public void resume() {
        consumer.resume(allPartitions());
    }

    private List<TopicPartition> allPartitions() {
        return consumer.partitionsFor(topic).stream()
                .map(partitionInfo -> new TopicPartition(partitionInfo.topic(), partitionInfo.partition()))
                .collect(Collectors.toList());
    }
}
