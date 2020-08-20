package com.hans;


/*1. neue Klasse - DbHelper
leere Methode OpenDatabase
in der Main Hello JDBC ausgeben
 */

import java.sql.*;

/* JAR - Java Archive --- Interface, Classe
https://github.com/johanngrabner-lv/DBP_SS2020/blob/master/sqlite-jdbc-3.30.1.jar
FILE - Project Structure
Libraries "+" -- sqlite-jdbc-3.30.1.jar
 */
public class DbHelper {

    private Connection con =null;

    public void OpenDatabase() {
        String connectionString="jdbc:sqlite:Host:128.10.12.14";
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

            stmt.executeUpdate("DROP TABLE Kunden");
            //Abschicken an die Datenbank
            //CREATE, INSERT, UPDATE, DELETE --- executeUpdate
            stmt.executeUpdate(createTable);

            stmt.executeUpdate("INSERT INTO Kunden VALUES('Aiste')");
            stmt.executeUpdate("INSERT INTO Kunden VALUES('Emina')");
            stmt.executeUpdate("INSERT INTO Kunden VALUES('Kerstin')");

            //SELECT -->  stmt.executeQuery()

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void ReadKunden()
    {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT rowid, vorname FROM  Kunden");
            //rs Zeiger auf das "virtuelle Ergebniss"
            //mit rs.Next() wird der Zeiger auf die nächste Zeile gesetzt
            /*
            rs.next(); //1. Zeile --> true, while eine Zeile vorhanden ist
            rs.next(); //2. Zeile --> true
            rs.next(); // 3. Zeile --> true
            rs.next(); //am Ende der Tabelle --> false
            */
            while(rs.next()){
                int rowid = rs.getInt("rowid");
                String vorname = rs.getString("Vorname");
                System.out.println("RowId " + rowid + " Vorname " + vorname);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

