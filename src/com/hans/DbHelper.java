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
        String createTable="CREATE TABLE Prod(Vorname varchar(20));";

        try {
            //Statment kapselt SQL-Anweisungen
            Statement stmt = con.createStatement();

            stmt.executeUpdate("DROP TABLE Prod");
            //Abschicken an die Datenbank
            //CREATE, INSERT, UPDATE, DELETE --- executeUpdate
            stmt.executeUpdate(createTable);

            stmt.executeUpdate("INSERT INTO Prod VALUES('Aiste')");
            stmt.executeUpdate("INSERT INTO Prod VALUES('Emina')");
            stmt.executeUpdate("INSERT INTO Prod VALUES('Kerstin')");

            //SELECT -->  stmt.executeQuery()

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void ReadKunden()
    {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT rowid, vorname FROM  Prod");
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
                int rowidByIndex = rs.getInt(1);
                String vorname = rs.getString("Vorname");
                System.out.println("RowId " + rowid + " Vorname " + vorname);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean tableExists(String tableName){
        String selectCount="";
        selectCount += "SELECT count(*) as Anzahl FROM sqlite_master WHERE type='table' ";
        selectCount += " and name='" + tableName + "'";

        boolean exists=false;

        try {
            Statement stmt= con.createStatement();
            ResultSet rs = stmt.executeQuery(selectCount);
            rs.next();
            if (rs.getInt("Anzahl")==0)
                exists=false;
            else
                exists=true;


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return exists;
    }


    public void createTable(String tableName, String ddl)
    {

        try {
            if (tableExists(tableName)==false)
            {
                String createSQL = "CREATE TABLE " + tableName;
                createSQL += ddl;

                Statement stmt= con.createStatement();
                stmt.executeUpdate(createSQL);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void addProdukt(String bezeichnung, double price)
    {
        String insertSQL = "INSERT INTO Produkte VALUES(";
        insertSQL += "'" + bezeichnung + "', ";
        insertSQL += price + ")";

        //INSERT INTO Produkte VALUES('Handy',20)

        try {
            Statement stmt= con.createStatement();

            stmt.executeUpdate(insertSQL);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void readAllProdukte(){
        String select="";
        select += "SELECT rowid, Produktbezeichnung, preis  ";
        select += " FROM Produkte";
        try {
            Statement stmt= con.createStatement();
            ResultSet rs = stmt.executeQuery(select);
            int counter=0;
            double gesamtPreis=0;
            while (rs.next()){
                System.out.println(rs.getInt("rowid")
                        + " " + rs.getString("Produktbezeichnung")
                        + " "
                        + rs.getDouble("Preis"));
                counter++;
                gesamtPreis += rs.getDouble("Preis");
            }
            System.out.println("Geht auch " + (gesamtPreis / counter));



            select = "SELECT AVG(Preis) AS Durchschnitt FROM Produkte";
             rs = stmt.executeQuery(select);
            rs.next();
            System.out.println("Durchschnitt " + rs.getDouble("Durchschnitt"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void addProduktPreparedStatement(String bezeichnung, double price)
    {
        String insertSQL = "INSERT INTO Produkte VALUES(?,?);";

        //INSERT INTO Produkte VALUES('Handy',20)

        try {
            PreparedStatement stmt= con.prepareStatement(insertSQL);
            stmt.setString(1,bezeichnung);
            stmt.setDouble(2,price);
            stmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void addProdukt(Produkt produkt)
    {
        String insertSQL = "INSERT INTO Produkte VALUES(?,?);";

        //INSERT INTO Produkte VALUES('Handy',20)

        try {
            PreparedStatement stmt= con.prepareStatement(insertSQL);
            stmt.setString(1,produkt.getProduktbezeichnung());
            stmt.setDouble(2,produkt.getPreis());
            stmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public List<Produkt> getAllProdukte(){
        List<Produkt> ergebnis =new ArrayList<Produkt>();
        String select="";
        select += "SELECT rowid, Produktbezeichnung, preis  ";
        select += " FROM Produkte";
        try {
            Statement stmt= con.createStatement();
            ResultSet rs = stmt.executeQuery(select);

            while (rs.next()){
                Produkt p=new Produkt();
                p.setProduktbezeichnung(rs.getString("Produktbezeichnung"));
                p.setRowid(rs.getInt("rowid"));
                p.setPreis(rs.getDouble("Preis"));
                ergebnis.add(p);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ergebnis;

    }

}

