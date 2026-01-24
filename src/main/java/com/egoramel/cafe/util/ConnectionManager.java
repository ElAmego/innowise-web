package com.egoramel.cafe.util;

import com.egoramel.cafe.exception.CustomException;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class ConnectionManager {
    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";
    private static final int POOL_SIZE = 10;
    private static BlockingQueue<Connection> connectionPool;

    static {
        loadDriver();
        initConnectionPool();
    }

    private static void loadDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (final ClassNotFoundException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    private static void initConnectionPool() {
        connectionPool = new ArrayBlockingQueue<>(POOL_SIZE);

        for (int i = 0; i < POOL_SIZE; i++) {
            final Connection connection = openConnection();
            final Class<ConnectionManager> connectionManagerClass = ConnectionManager.class;
            final ClassLoader classLoader = connectionManagerClass.getClassLoader();
            final Connection proxyConnection = (Connection) Proxy.newProxyInstance(classLoader,
                    new Class[]{Connection.class}, ((proxy, method, args) ->
                            method.getName().equals("close") ? connectionPool.add((Connection) proxy) :
                                    method.invoke(connection, args)));
            connectionPool.add(proxyConnection);

        }
    }

    private static Connection openConnection() {
        try {
            return DriverManager.getConnection(
                    PropertiesUtil.getPropertyValue(URL_KEY),
                    PropertiesUtil.getPropertyValue(USERNAME_KEY),
                    PropertiesUtil.getPropertyValue(PASSWORD_KEY)
            );
        } catch (final SQLException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    public static Connection getConnection() throws CustomException {
        try {
            return connectionPool.take();
        } catch (final InterruptedException e) {
            throw new CustomException(e);
        }
    }
}