package com.hans;

import java.sql.*;

public class DbHelper {
    Connection con = null;

    public void OpenDatabase() {
        try {
            con = DriverManager.getConnection("jdbc:sqlite:myFirstDatabase");
            Statement statement = con.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate("create table Kunden(i int)");
            statement.executeUpdate("insert into Kunden values(2)");
            ResultSet rs = statement.executeQuery("select * from kunden");
            while (rs.next()) {
                System.out.println(rs.getInt(1));
            }
            statement.executeUpdate("drop table kunden");

        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

