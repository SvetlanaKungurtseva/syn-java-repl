package sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect { // Подключаемся к базе
    public static void connect() {
        Connection co = null;
        try {
            String url = "jdbc:sqlite:sq.db";
            //String url = "jdbc:sqlite:zb.db";
            co = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");

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
