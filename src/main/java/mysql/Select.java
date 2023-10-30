package mysql;

import java.sql.*;

public class Select {

    private Connection connect() { // Читаем из базы
        Connection co = null;
        try {
            co = DriverManager.getConnection("jdbc:mariadb://localhost:3306/somedb", "someuser", "somepass");
            System.out.println("Connection to Maria has been established.");

            // Можно и такую версию сделать добавление и создание таблиц перед тем как работать с базой
            co.createStatement().execute("create table if not exists Users (\n" +
                    " id int primary key auto_increment,\n" +
                    " name varchar(20) not null,\n" +
                    " phone varchar(20) default null\n" +
                    ");");
            System.out.println("Tables created.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return co;
    }

    public void selectAll() {
        //String sql = "SELECT id, name, phone from Users WHERE name LIKE '%Petya%';";
        String sql = "select * from Users;";

        // Создаю строки таблицы
        try (Connection co = this.connect()) {
            String sqlCreate = "insert into Users (name, phone) values\n" +
                    "('Petya', '1234567'),\n" +
                    "('Vasya', '6543219'),\n" +
                    "('Katya', null);";

            Statement stmtCreate = co.createStatement();
            stmtCreate.executeQuery(sqlCreate);

            Statement stmt = co.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                        rs.getString("name") + "\t" +
                        rs.getString("phone"));
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
