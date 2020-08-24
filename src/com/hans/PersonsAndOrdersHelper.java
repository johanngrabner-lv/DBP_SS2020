package com.hans;


/*1. neue Klasse - DbHelper
leere Methode OpenDatabase
in der Main Hello JDBC ausgeben
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/* JAR - Java Archive --- Interface, Classe
https://github.com/johanngrabner-lv/DBP_SS2020/blob/master/sqlite-jdbc-3.30.1.jar
FILE - Project Structure
Libraries "+" -- sqlite-jdbc-3.30.1.jar
 */
public class PersonsAndOrdersHelper {

    private Connection con =null;

    public void OpenDatabase() {
        String connectionString="jdbc:sqlite:dbHans";
        try {
            con = DriverManager.getConnection(connectionString);
            Statement stmt = con.createStatement();
            stmt.executeUpdate("PRAGMA foreign_keys=on;");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void ForeignKeyDemo(){
        String createTable="CREATE TABLE Persons\n" +
                "(" +
                "  PId INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  Firstname varchar(30)\n" +
                ");";

        try {

            Statement stmt = con.createStatement();
           // stmt.executeUpdate(createTable);
            createTable="CREATE TABLE Orders (" +
                     "    OrderNumber int NOT NULL," +
                    "    PersonID int," +
                    "    FOREIGN KEY (PersonID) REFERENCES Persons(PId)\n" +
                    ");";
          //  stmt.executeUpdate(createTable);

            stmt.executeUpdate("INSERT INTO Persons(Firstname) Values('Hans');");
            stmt.executeUpdate("INSERT INTO Persons(Firstname) Values('Maria');");
            stmt.executeUpdate("\n" +
                    "INSERT INTO Orders(OrderNumber,PersonId) Values(123,1);");
            stmt.executeUpdate("DELETE From Orders where PersonID = 1;");
            stmt.executeUpdate("DELETE From Persons where pid = 1;");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}

