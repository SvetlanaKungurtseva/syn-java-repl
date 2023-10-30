package sqlite;

import java.sql.*;

public class Select {

    private Connection connect() { // Читаем из базы
        String url = "jdbc:sqlite:sq.db";
        //String url = "jdbc:sqlite:12345.db";
        //String url = "jdbc:sqlite:zb.db";
        Connection co = null;
        try {
            co = DriverManager.getConnection(url);
            // Можно и такую версию сделать добавление и создание таблиц перед тем как работать с базой
            co.createStatement().execute("create table if not exists Users (\n" +
                    " id integer primary key autoincrement,\n" +
                    " name varchar(20) not null,\n" +
                    " phone varchar(20) default null\n" +
                    ");");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return co;
    }

    public void selectAll() {
        String sql = "SELECT id, name, phone from Users WHERE name LIKE '%Petya%';";
         //String sql = "select * from Users;";
        //String sql = "select * from Persons;";

        try (Connection co = this.connect();
             Statement stmt = co.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                        rs.getString("name") + "\t" +
                        rs.getString("phone"));
               /* System.out.println(rs.getInt("employee_id") + "\t" +
                        rs.getString("first_name") + "\t" +
                        rs.getString("last_name"));*/
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) {
        Select app = new Select();
        app.selectAll();
    }
}
