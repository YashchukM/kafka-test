package org.myas.kafka;

import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.junit.jupiter.api.Test;
import org.myas.kafka.utils.PropertiesHelper;

/**
 * @author Mykhailo Yashchuk
 */
public class AdminTest {
    public static final String BROKERS = System.getProperty("kafka.brokers");

    @Test
    public void testCreateTopic() throws Exception {
        Properties properties = PropertiesHelper.defaultAdminProperties(BROKERS);
        try (AdminClient adminClient = AdminClient.create(properties)) {
            NewTopic topic = new NewTopic("my_topic", 1, (short) 1);
            adminClient.createTopics(Collections.singleton(topic)).all().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testListTopics() throws Exception {
        Properties properties = PropertiesHelper.defaultAdminProperties(BROKERS);
        try (AdminClient adminClient = AdminClient.create(properties)) {
            adminClient.listTopics().names().get().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteTopic() throws Exception {
        Properties properties = PropertiesHelper.defaultAdminProperties(BROKERS);
        try (AdminClient adminClient = AdminClient.create(properties)) {
            adminClient.deleteTopics(Arrays.asList("test", "my_topic"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
