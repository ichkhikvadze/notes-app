package com.example.notesapp.databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {

    private static final Logger LOGGER = Logger.getLogger(DatabaseConnection.class.getName());

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "",
                    "");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Getting connection from database has failed");
        }

        return null;
    }
}
