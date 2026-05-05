package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // --- Configuration ---
    private static final String URL      = "jdbc:mysql://localhost:3306/university_db";
    private static final String USER     = "root";       
    private static final String PASSWORD = "KRohini@2004"; 

    // --- Single shared connection ---
    private static Connection connection = null;

    // --- Get connection (creates one if not already open) ---
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                // Load the MySQL driver
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Create the connection
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Database connected successfully!");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("❌ MySQL Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("❌ Connection failed: " + e.getMessage());
        }
        return connection;
    }

    // --- Close the connection when done ---
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("🔒 Connection closed.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error closing connection: " + e.getMessage());
        }
    }
}