package sqlite;

import java.sql.*;

public class Update {
    private Connection connect() { // Читаем из базы
        String url = "jdbc:sqlite:sq.db";

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


    public void update() {
        String sql = "update Users set name=? where id=?"; // Знак ? это наш наполнитель, который мы можем указать вместо того чтобы заполнять данными
        try (Connection co = this.connect();
             PreparedStatement stmt = co.prepareStatement(sql)) {
            stmt.setString(1, "Henry");
            stmt.setInt(2, 2);
            stmt.executeUpdate();

            System.out.println("Database update successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) {
        Update app = new Update();
        app.update();
    }
}
