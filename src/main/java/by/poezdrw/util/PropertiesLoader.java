package by.poezdrw.util;

import by.poezdrw.exception.ConfigException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class PropertiesLoader {

    private static final Logger logger = LogManager.getLogger(PropertiesLoader.class);

    private static final String CONFIG_FILE_NAME = "properties" + File.separator + "config.properties";

    public static Properties config = loadProperties(CONFIG_FILE_NAME);


    private static Properties loadProperties(String filePath) throws ConfigException {
        Properties properties = new Properties();
        try (InputStream inputStream = PropertiesLoader.class.getClassLoader().getResourceAsStream(filePath)) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new ConfigException("property file '" + CONFIG_FILE_NAME + "' not found in the classpath");
            }
        } catch (IOException e) {
            logger.log(Level.FATAL, "Unable to load config data to start a program: " + e);
            throw new ConfigException("Unable to load config data from file: " + CONFIG_FILE_NAME);
        }

        return properties;
    }

    /**
     * Method to get value from config.properties file or EMPTY String if key is absent
     * @param key ignoring case
     * @return {@link String} value of properties according to key parameter, or EMPTY String if key is absent
     */
    public static String getPropertyValue(String key) {
        String result = "";
        Set<Object> set = config.keySet();

        for (Object obj : set) {
            String str = obj.toString();
            if (str.equalsIgnoreCase(key)) {
                result = config.getProperty(str);
            }
        }

        return result;
    }

}
