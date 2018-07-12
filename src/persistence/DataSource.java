package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

    private final String DB_URL = "jdbc:postgresql://localhost/star_db";
    private final String USER = "postgres";
    private final String PASS = "basididati";
    private Connection connection;

    public Connection getConnection() throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(DB_URL, USER, PASS);
        return connection;
    }
}

