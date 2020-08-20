package com.hans;


/*1. neue Klasse - DbHelper
leere Methode OpenDatabase
in der Main Hello JDBC ausgeben
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/* JAR - Java Archive --- Interface, Classe
https://github.com/johanngrabner-lv/DBP_SS2020/blob/master/sqlite-jdbc-3.30.1.jar
FILE - Project Structure
Libraries "+" -- sqlite-jdbc-3.30.1.jar
 */
public class DbHelper {

    private Connection con =null;

    public void OpenDatabase() {
        String connectionString="jdbc:sqlite:dbHans";
        try {
            con = DriverManager.getConnection(connectionString);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void CreateKundenTable(){
        String createTable="CREATE TABLE Kunden(Vorname varchar(20));";

        try {
            //Statment kapselt SQL-Anweisungen
            Statement stmt = con.createStatement();

            //Abschicken an die Datenbank
            stmt.executeUpdate(createTable);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

