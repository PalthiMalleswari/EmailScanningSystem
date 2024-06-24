package com.malleswari;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/email_scanner_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Malleswari@143";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // For MySQL Connector/J 8.x
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load MySQL driver", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
