package com.revature.ReimbursementSystem.Util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static final ConnectionFactory connectionFactory = new ConnectionFactory();
    private Properties properties = new Properties();

    private ConnectionFactory() {
        /* Lazy
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
         */
        try {
            properties.load(new FileReader("src/main/resources/db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"), properties.getProperty("password"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
