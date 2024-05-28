package database.config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfiguration {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3307/incercare_3";
    private static final String USER = "User";
    private static final String PASSWORD = "MyRandomPassword";
    private static Connection connection;


    private DatabaseConfiguration() {}

    public static Connection getDatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to database.");
        }
        return connection;
    }

    public static void closeDatabaseConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
