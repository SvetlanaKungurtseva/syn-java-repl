package postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect { // Подключаемся к базе
    public static void connect() {
        Connection co = null;
        try {
            String url = "jdbc:postgresql://localhost:5432/somedb"; // Драйвер:протокол
            co = DriverManager.getConnection(url, "someuser", "somepass");
            System.out.println("Connection to Postgres has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (co != null) {
                    co.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        connect();
    }
}
