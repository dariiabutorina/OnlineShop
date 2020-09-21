package com.internet.shop.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Can't find MySQL Driver", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection connection;
        Properties connectionProps = new Properties();
        connectionProps.put("user","root");
        connectionProps.put("password", "Password");
        String url = "jdbc:mysql://localhost:3306/TopStore?serverTimezone=UTC";

        try {
            connection = DriverManager.getConnection(url, connectionProps);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
