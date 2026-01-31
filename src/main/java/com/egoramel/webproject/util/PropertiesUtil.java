package com.egoramel.webproject.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertiesUtil {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    private PropertiesUtil() {

    }

    private static void loadProperties() {
        final Class<PropertiesUtil> propertiesUtilImplClass = PropertiesUtil.class;
        final ClassLoader classLoader = propertiesUtilImplClass.getClassLoader();
        try (final InputStream inputStream = classLoader.getResourceAsStream("application.properties")) {
            PROPERTIES.load(inputStream);
        } catch (final IOException exception) {
            LOGGER.fatal("A critical error occurred while loading properties.");
            throw new ExceptionInInitializerError(exception);
        }
    }

    public static String getPropertyValue(final String key) {
        return PROPERTIES.getProperty(key);
    }
}