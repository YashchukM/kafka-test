package org.myas.kafka;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Test;
import org.myas.kafka.consumer.BasicKafkaConsumer;
import org.myas.kafka.consumer.KafkaConsumer;
import org.myas.kafka.producer.BasicKafkaProducer;
import org.myas.kafka.producer.KafkaProducer;
import org.myas.kafka.utils.PropertiesHelper;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ProduceConsumeTest {
    private static final String BROKERS = System.getProperty("kafka.brokers");

    private KafkaProducer<String, String> producer;
    private KafkaConsumer<String, String> consumer;

    @Test
    void testProduceConsume() throws InterruptedException {
        producer = new BasicKafkaProducer<>(PropertiesHelper.defaultProducerProperties(BROKERS, "my_producer"),
                "my_topic", new StringSerializer(), new StringSerializer());
        consumer = new BasicKafkaConsumer<>(PropertiesHelper.defaultConsumerProperties(BROKERS, "my_consumer"),
                "my_topic", new StringDeserializer(), new StringDeserializer());
        consumer.init();

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(() -> {
            consumer.start(record -> {
                System.out.printf("%s %s\n", record.value(), record.offset());
            });
        });
        executorService.shutdown();

        for (int i = 0; i < 100; i++) {
            producer.send(Integer.toString(i));
        }

        executorService.awaitTermination(2, TimeUnit.SECONDS);
        consumer.stop();
    }

    @Test
    void testProduce() throws InterruptedException, ExecutionException {
        producer = new BasicKafkaProducer<>(PropertiesHelper.defaultProducerProperties(BROKERS, "my_producer"),
                "my_topic", new StringSerializer(), new StringSerializer());

        Future<RecordMetadata> fut = producer.sendAsync("test");
        System.out.println(fut.get().offset());
    }

    @Test
    void testConsume() throws InterruptedException {
        consumer = new BasicKafkaConsumer<>(PropertiesHelper.defaultConsumerProperties(BROKERS, "my_consumer"),
                "my_topic", new StringDeserializer(), new StringDeserializer());

        consumer.init();
        consumer.start(record -> {
            System.out.printf("%s %s\n", record.value(), record.offset());
        });
    }
}
