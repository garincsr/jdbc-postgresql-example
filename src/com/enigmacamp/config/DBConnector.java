package com.enigmacamp.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    public static Connection getConnection(){
        try {
            String dbHost = "localhost";
            String dbPort = "5432";
            String dbName = "enigma_laundry";
            String username = "postgres";
            String password = "postgres";
            String url = String.format("jdbc:postgresql://%s:%s/%s",dbHost,dbPort,dbName);
            return DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
