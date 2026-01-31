package com.egoramel.webproject.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";
    private static final Integer POOL_SIZE = 10;
    private static BlockingQueue<Connection> releasedConnections;
    private static BlockingQueue<Connection> occurredConnections;

    static {
        loadDriver();
        initConnectionPool();
    }

    private static void loadDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (final ClassNotFoundException e) {
            LOGGER.fatal("A critical error occurred while loading PostgreSQL driver.");
            throw new ExceptionInInitializerError(e);
        }
    }

    private static void initConnectionPool() {
        try {
            releasedConnections = new ArrayBlockingQueue<>(POOL_SIZE);
            occurredConnections = new ArrayBlockingQueue<>(POOL_SIZE);

            for (int i = 0; i < POOL_SIZE; i++) {
                final String url = PropertiesUtil.getPropertyValue(URL_KEY);
                final String user = PropertiesUtil.getPropertyValue(USERNAME_KEY);
                final String password = PropertiesUtil.getPropertyValue(PASSWORD_KEY);
                final Connection connection = DriverManager.getConnection(url, user, password);

                releasedConnections.offer(connection);
            }
        } catch (final SQLException e) {
            throw new ExceptionInInitializerError("DB Connection failed.");
        }
    }

    public static Connection getConnection() {
        Connection connection = null;

        try {
            connection = releasedConnections.take();
            occurredConnections.offer(connection);
        } catch (final InterruptedException e) {
            LOGGER.error("An error occurred while getting connection.");
            Thread.currentThread().interrupt();
        }

        return connection;
    }

    public static void releaseConnection(final Connection connection) {
        if (connection != null) {
            occurredConnections.remove(connection);

            try {
                releasedConnections.put(connection);
            } catch (final InterruptedException e) {
                LOGGER.error("An error occurred while releasing connection.");
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void destroyPool() {
        while (!releasedConnections.isEmpty()) {
            try {
                Connection conn = releasedConnections.poll();

                if (conn != null) {
                    conn.close();
                }
            } catch (final SQLException e) {
                LOGGER.error("An error occurred while destroying connection.");
            }
        }
    }
}