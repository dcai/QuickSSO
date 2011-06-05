package cn.edu.ujn.util;

import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;
/**
 * LightningBoard configuration, configuration file:
 *  "/lightningboard.properties".
 * @version 0.3.5
 * @author Xiaobo Liu
 */
public class Configuration {
    private Properties properties;
    private final static Configuration cfg = new Configuration();

    private Configuration() {
        properties = new Properties();
        InputStream is = null;
        try {
            is = getClass().getResourceAsStream("/uso.properties");
            properties.load(is);
        } catch (Exception exception) {
          System.out.println("Can't read the properties file. ");
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException exception) {
                // ignored
            }
        }
    }
    /**
     * Use singleton pattern, only return one instance of Configuration.
     * @return Configuration
     */
    public static Configuration getInstance() {
      return cfg;
    }
    /**
     * Retun a value for certain key.
     * @param key a certain key define in properties file.
     * @return value
     */
    public String getValue(String key) {
        return properties.getProperty(key);
    }
}
