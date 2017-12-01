package org.myas.kafka.utils;

import java.util.Properties;

import org.apache.kafka.clients.admin.AdminClientConfig;

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
}
