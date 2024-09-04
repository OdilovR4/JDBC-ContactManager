package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseUtil {

    public static void createTable() {

        String sql = "create table if not exists contact (" +
                "id serial primary key," +
                "name varchar(25) not null, " +
                "surname varchar(25) not null," +
                "phone varchar(12) not null unique" +
                ")";

        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/db_lesson_jon", "db_user_jon", "Odilov2004");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
