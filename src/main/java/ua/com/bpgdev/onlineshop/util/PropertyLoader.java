package ua.com.bpgdev.onlineshop.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
    public Properties loadProperties(String propertyFileName)
    {
        final File propertyFile = new File(propertyFileName);
        try (final InputStream inputStream = propertyFile.isFile() ?
                new FileInputStream(propertyFile) :
                this.getClass().getResourceAsStream(propertyFileName)
        ) {
            if (inputStream != null) {
                Properties properties = new Properties();
                properties.load(inputStream);
                return properties;
            }
            else {
                throw new IllegalArgumentException("Cannot find property file: " + propertyFileName);
            }
        }
        catch (IOException io) {
            throw new RuntimeException("Failed to read property file", io);
        }
    }

}
