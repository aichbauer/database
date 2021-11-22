package services;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;


public class Database {
    private Connection connection;

    public Database() {
        try {
            // !!! NEVER COMMIT DATABASE CREDENTIALS TO A GIT REPOSITORY !!!
            this.connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5431/swe1db",
                "swe1user",
                "swe1pw"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void close() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
