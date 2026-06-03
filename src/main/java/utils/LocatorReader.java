package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LocatorReader {

    private static Properties props = new Properties();

    static {
        try {
            FileInputStream fis = new FileInputStream(
                    "src/test/resources/locators.properties");
            props.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Cannot load locators.properties: " + e);
        }
    }

    public static String get(String key) {
        String value = props.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Locator key not found: " + key);
        }
        return value.trim();
    }
}
