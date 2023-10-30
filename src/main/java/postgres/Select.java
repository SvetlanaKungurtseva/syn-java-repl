package postgres;

import models.Contact;
import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Select {

    private Connection connect() { // Читаем из базы
        Connection co = null;
        try {
            co = DriverManager.getConnection("jdbc:postgresql://localhost:5432/somedb", "someuser", "somepass");
            System.out.println("Connection to Postgres has been established.");

            // Можно и такую версию сделать добавление и создание таблиц перед тем как работать с базой
            co.createStatement().execute("create table if not exists \"Users\" (\n" +
                    " id int generated always as identity,\n" +
                    " name varchar(20) not null,\n" +
                    " primary key(id)\n" +
                    ");\n" +
                    "\n" +
                    "create table if not exists \"Contacts\" (\n" +
                    " id int generated always as identity,\n" +
                    " \"customerId\" int,\n" +
                    "  \"contactName\" varchar(255) not null,\n" +
                    " phone varchar(15),\n" +
                    " email varchar(100),\n" +
                    " primary key(id),\n" +
                    " constraint fk_customer\n" +
                    " foreign key(\"customerId\")\n" +
                    " references \"Users\" (id)\n" +
                    ");");
            System.out.println("Tables created.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return co;
    }

    public void selectAll() {
        //String sql = "SELECT id, name from "Users" WHERE name LIKE '%Petya%';";
        //String sql = "select * from \"Users\";";  // select * from "Users" u left join "Contacts" c on c."customerId" = u.id;
        //String sql = "select * from \"Users\" u left join \"Contacts\" c on c.\"customerId\" = u.id;";
        String sql = "select u.id as \"User->id\", u.name as \"Users->name\", c.id as \"contactId\", c.\"contactName\", c.phone, c.email from \"Users\" u left join \"Contacts\" c on c.\"customerId\" = u.id order by \"User->id\";";
        List<User> users = new ArrayList<>();
        // Создаю строки таблицы
        try (Connection co = this.connect()) {
            Statement stmt = co.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // loop through the result set
            User tmpUser = null;
            List<Contact> tmpContacts = new ArrayList<>();

            while (rs.next()) {
                if (tmpUser != null && tmpUser.id != rs.getInt("User->id")) {
                    tmpUser.contacts = tmpContacts;
                    users.add(tmpUser);
                    tmpUser = null; // Чистим контакты как добавили юзера
                }
                tmpUser = new User(rs.getInt("User->id"), rs.getString("Users->name"));
                tmpContacts.add(new Contact(rs.getInt("contactId"), rs.getInt("User->id"), rs.getString("contactName"), rs.getString("phone"), rs.getString("email")));
            }
            if (tmpUser != null) {
                tmpUser.contacts = tmpContacts;
                users.add(tmpUser);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(users);

    }


    public static void main(String[] args) {
        Select app = new Select();
        app.selectAll();
    }
}
