package manage_db;


import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;

public class ConectionDB {

    private final String jdbcUrl;
    private final String username;
    private final String password;

    public ConectionDB() {

        Dotenv dotenv = Dotenv.load();

        jdbcUrl = dotenv.get("JDBCURL");
        username = dotenv.get("DB_USERNAME");
        password = dotenv.get("DB_PASSWORD");

    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, username, password);
    }
}