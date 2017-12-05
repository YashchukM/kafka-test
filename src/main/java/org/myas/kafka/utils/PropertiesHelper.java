package org.myas.kafka.utils;

import java.util.Properties;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;

/**
 * @author Mykhailo Yashchuk
 */
public class PropertiesHelper {
    public static Properties defaultAdminProperties(String servers) {
        Properties properties = new Properties();
        properties.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        properties.setProperty(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, "3000");
        return properties;
    }

    public static Properties defaultProducerProperties(String servers, String clientId) {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArraySerializer");
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArraySerializer");
        properties.setProperty(ProducerConfig.ACKS_CONFIG, "1");
        properties.setProperty(ProducerConfig.CLIENT_ID_CONFIG, clientId);
        return properties;
    }

    public static Properties defaultConsumerProperties(String servers, String clientId) {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_DOC,
                "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        properties.setProperty(ConsumerConfig.CLIENT_ID_CONFIG, clientId);
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, clientId);
        return properties;
    }
}
