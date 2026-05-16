package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/gestionabsence?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    private static Connection connection = null;

    private DBConnection() {
        // Private constructor to prevent instantiation
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                // Register driver manually (optional for newer JDBC, but good practice)
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (ClassNotFoundException e) {
                throw new SQLException("MySQL Driver not found", e);
            }
        }
        return connection;
    }
}
