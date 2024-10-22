package database;


import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;

public class DatabaseConnection {

    private final String jdbcUrl;
    private final String username;
    private final String password;

    public DatabaseConnection() {

        Dotenv dotenv = Dotenv.load();

        jdbcUrl = dotenv.get("JDBCURL");
        username = dotenv.get("DB_USERNAME");
        password = dotenv.get("DB_PASSWORD");

    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, username, password);
    }
}