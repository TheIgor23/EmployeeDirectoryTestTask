package ru.jezemoin.emploedirectory.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBUtil {

    private static final String CONNECTION_URL = DatabaseConfig.CONNECTION_STRING;
    private static final String USER_NAME = DatabaseConfig.DB_USER_NAME;
    private static final String PASSWORD = DatabaseConfig.DB_PASSWORD;

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONNECTION_URL, USER_NAME, PASSWORD);
    }

}
