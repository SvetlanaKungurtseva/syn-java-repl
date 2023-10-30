package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect { // Подключаемся к базе
    public static void connect() {
        Connection co = null;
        try {
            String url = "jdbc:mariadb://localhost:3306/somedb"; // Драйвер:протокол
            co = DriverManager.getConnection(url, "someuser", "somepass");
            System.out.println("Connection to Maria has been established.");

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
