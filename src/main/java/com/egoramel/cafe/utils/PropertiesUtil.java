package com.egoramel.cafe.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertiesUtil {
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
            throw new UnsupportedOperationException(exception);
        }
    }

    public static String getPropertyValue(final String key) {
        return PROPERTIES.getProperty(key);
    }
}