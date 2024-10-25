package ru.jezemoin.emploedirectory.util;


import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


@Log4j2
public class DatabaseConfig {
    static Properties prop = new Properties();

    static {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream("application.properties");
        try {
            prop.load(input);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public final static String DB_HOST = prop.getProperty("db.host");
    public final static String DB_PORT = prop.getProperty("db.port");
    public final static String DB_NAME = prop.getProperty("db.name");
    public final static String DB_USER_NAME = prop.getProperty("db.username");
    public final static String DB_PASSWORD = prop.getProperty("db.password");
    public final static String CONNECTION_STRING = "jdbc:postgresql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;
}
