package foo.guilherme.connector;

import java.util.Properties;

/**
 * Connector configs
 */
public class ConnectorConfig {
    private final Properties configs;

    public ConnectorConfig(Properties configs) {
        this.configs = configs;
    }

    public String getValue(String key) {
        return this.configs.getProperty(key);
    }
}
