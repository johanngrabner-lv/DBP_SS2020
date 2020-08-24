package com.hans;


/*1. neue Klasse - DbHelper
leere Methode OpenDatabase
in der Main Hello JDBC ausgeben
 */

import javax.swing.*;
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

    //CREATE TABLE
    public void Demo1DDLStatementCreatePersonen(){

        String ddlCreatePersonen="CREATE TABLE Personen (Vorname varchar(20), Punkte decimal(10,2))";

        try {
            Statement   stmt = con.createStatement();
            stmt.executeUpdate(ddlCreatePersonen);
            System.out.println( "Tabelle wurde erstellt");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public boolean tableExists(String tableName){
        String selectCount="";
        selectCount += "SELECT * FROM sqlite_master WHERE type='table' ";
        selectCount += " and name='" + tableName + "'";

        boolean exists=false;

        try {
            Statement stmt= con.createStatement();
            ResultSet rs = stmt.executeQuery(selectCount);
            if (rs.next()==false) //es wurde kein Datensatz gefunden
                    exists=false;
            else
                exists=true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return exists;
    }

    public void ReadAllTables(){
        String selectAllTables="";
        selectAllTables += "SELECT * FROM sqlite_master WHERE type='table' ";


        try {
            Statement stmt= con.createStatement();
            ResultSet rs = stmt.executeQuery(selectAllTables);

            while(rs.next())
            {
                System.out.println("Tabellenname: " + rs.getString("Name"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public int Demo2DMLInsertPersonen(String vorname, double punkte){
//        String ddlCreatePersonen="CREATE TABLE Personen (Vorname varchar(20), Punkte decimal(10,2))";

        String insertAsPreparedStmt = "INSERT INTO Personen(Vorname, Punkte) VALUES(?,?)";

        int affected = 0;
        try {
            PreparedStatement stmt= con.prepareStatement(insertAsPreparedStmt);
             stmt.setString(1,vorname);
            stmt.setDouble(2,punkte);
              affected = stmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return affected;

    }

    public void ReadAllPersonen(){

        String selectAllPersonen = "SELECT rowid, * FROM  Personen";

        int affected = 0;
        try {
            PreparedStatement stmt= con.prepareStatement(selectAllPersonen);
           ResultSet rs = stmt.executeQuery();
           while(rs.next()){
               System.out.println(
                       "rowid " +
                       rs.getInt("rowid") +
                       "Vorname " +
                               rs.getString("Vorname") +
                               "Punkte " +
                               rs.getDouble("Punkte"));

               System.out.println("null oder 0");

           }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    //DML
    //DQL - while
    //DQL - if (rs.next

}

