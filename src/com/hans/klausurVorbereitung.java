package com.hans;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class klausurVorbereitung {
    private Connection con =null;
    Statement stmt=null;
    PreparedStatement pStmt =null;

    public void init() {
        String connectionString="jdbc:sqlite:dbKlausurvorbereitungV1";
        try {
            con = DriverManager.getConnection(connectionString);
            stmt = con.createStatement();
            stmt.executeUpdate("PRAGMA foreign_keys=on;");
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public Statement getStatement(){

        Statement stmt=null;
        try {
            stmt=  con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  stmt;
    }
    private int executeUpdate(String sql) {
        int affected = 0;
        try {
            affected = stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  affected;
    }
    public int dropKundenTable() {
        String ddl = "DROP TABLE Kunden; ";
        System.out.println("DDL to drop the Kunden-Table " +  ddl);

        return  -99;
      // return executeUpdate(ddl);
    }
    public int createKundenTable(){
        String ddl="CREATE TABLE ";
        ddl += " Kunden(KDNR INTEGER PRIMARY KEY AUTOINCREMENT, ";
        ddl += " Vorname VARCHAR(20), ";
        ddl += " Nachname VARCHAR(20), ";
        ddl += " Geschlecht VARCHAR(5), ";
        ddl += " Bonuspunkte decimal(10,2) ";
        ddl += ")";
        System.out.println("DDL to create the Kunden-Table " +  ddl);

      //  return -99;

       return executeUpdate(ddl);

    }
    public int dropRechnungenTable() {
        String ddl = "DROP TABLE Rechnungen; ";
        System.out.println("DDL to drop the Rechnungen-Table " +  ddl);

        //return  -99;
        return executeUpdate(ddl);
    }
    public int createRechnungenTable(){
        String ddl="CREATE TABLE ";
        ddl += " Rechnungen(RENR INTEGER PRIMARY KEY AUTOINCREMENT, ";

        ddl += " Kdnr INTEGER, ";
        ddl += " Datum varchar(20), ";
        ddl += " Gesamtbetrag decimal(10,2), ";
        ddl += " FOREIGN KEY (Kdnr) REFERENCES Kunden(Kdnr)";
        ddl += ")";
        System.out.println("DDL to create the Rechnungen-Table " +  ddl);

        //return -99;

         return executeUpdate(ddl);
    }
    private PreparedStatement getPreoparedStatement(String sql)
    {
        PreparedStatement pStmt=null;

        try {
            pStmt= con.prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  pStmt;
    }

    public int getLastInsertRowid()
    {
        String sqlText = "SELECT last_insert_rowid() as rowid;";
        ResultSet rs = null;
        int lastId=0;
        try {
            rs = stmt.executeQuery(sqlText);
            rs.next();
            lastId=rs.getInt("rowid");
            rs.close();
            stmt.close();
            //con.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
       return  lastId;
    }
    public int insertKundeAndSetNewId(Kunde neuerKunde){
        String insertSQL="INSERT INTO Kunden(Vorname, Nachname, Geschlecht, Bonuspunkte) ";
        insertSQL += "Values(?,?,null,?)";

        int newAutoIncrementValue = 0;

        try {

           pStmt= getPreoparedStatement(insertSQL);
            pStmt.setString(1,neuerKunde.getVorname());
            pStmt.setString(2,neuerKunde.getNachname());
          //  pStmt.setString(3,neuerKunde.getGeschlecht());
            pStmt.setDouble(3,neuerKunde.getBonuspunkte());

            pStmt.executeUpdate();

            newAutoIncrementValue = getLastInsertRowid();
            neuerKunde.setKdnr(newAutoIncrementValue);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return newAutoIncrementValue;

    }
    public int insertRechnungAndSetNewId(Rechnung neueRechnung, Kunde vorhandenerKunden){
        String insertSQL="INSERT INTO Rechnungen(Kdnr, Gesamtbetrag, Datum) ";
        insertSQL += "Values(?,?,?)";

        int newAutoIncrementValue = 0;

        try {

            pStmt= getPreoparedStatement(insertSQL);
            pStmt.setInt(1,vorhandenerKunden.getKdnr());
            pStmt.setDouble(2,neueRechnung.getGesamtbetrag());
            pStmt.setString(3,neueRechnung.getDatum());

            pStmt.executeUpdate();

            newAutoIncrementValue = getLastInsertRowid();
            neueRechnung.setRenr(newAutoIncrementValue);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return newAutoIncrementValue;

    }
    public Kunde getKundeByKdnr(int kdnr){
        Kunde k =new Kunde();
        String select="";
        select += "SELECT KDNR, Vorname, Nachname, Geschlecht, Bonuspunkte  ";
        select += " FROM Kunden WHERE Kdnr = ? " ;
        try {
            PreparedStatement prepStatement= con.prepareStatement(select);
            prepStatement.setInt(1,kdnr);
           // Statement stmt= con.createStatement();
            ResultSet rs = prepStatement.executeQuery();

            if (rs.next()){

                k.setKdnr(rs.getInt("Kdnr"));
                k.setVorname(rs.getString("Vorname"));

                if (rs.wasNull())
                    k.setVorname("Vorname war null");

                k.setNachname(rs.getString("Nachname"));
                k.setGeschlecht(rs.getString("Geschlecht"));
                if (rs.wasNull())
                    k.setGeschlecht("Unbekannt");

                String geschlecht = rs.getString("Geschlecht");
                if (geschlecht==null)
                    k.setGeschlecht("unbekannt");


                k.setBonuspunkte(rs.getDouble("Bonuspunkte"));

                Objekt <--> Row

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return k;

    }
    public List<Kunde> getAlleKunden() {
        List<Kunde> ergebnis = new ArrayList<Kunde>();
        String select = "";
        select += "SELECT KDNR ";
        select += " FROM Kunden ";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(select);

            while (rs.next()) {

                ergebnis.add(getKundeByKdnr(rs.getInt("KDNR")));

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  ergebnis;
    }

        public List<Kunde> getAlleWeiblichenKundenInnen() {
            List<Kunde> ergebnis = new ArrayList<Kunde>();
            String select = "";
            select += "SELECT KDNR ";
            select += " FROM Kunden WHERE Geschlecht='Frau'";
            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(select);

                while (rs.next()) {

                    ergebnis.add(getKundeByKdnr(rs.getInt("KDNR")));

                }


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return ergebnis;

        }

    public List<Kunde> getAlleKundenAlt() {
        List<Kunde> ergebnis = new ArrayList<Kunde>();
        String select = "";
        select += "SELECT KDNR, Vorname, Nachname, Geschlecht, Bonuspunkte  ";
        select += " FROM Kunden ";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(select);

            while (rs.next()) {
                Kunde k = new Kunde();
                k.setKdnr(rs.getInt("Kdnr"));
                k.setVorname(rs.getString("Vorname"));

                if (rs.wasNull())
                    k.setVorname("Vorname war null");

                k.setNachname(rs.getString("Nachname"));
                k.setGeschlecht(rs.getString("Geschlecht"));
                if (rs.wasNull())
                    k.setGeschlecht("Unbekannt");

                String geschlecht = rs.getString("Geschlecht");
                if (geschlecht == null)
                    k.setGeschlecht("unbekannt");


                k.setBonuspunkte(rs.getDouble("Bonuspunkte"));

                ergebnis.add(k);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ergebnis;

    }

    public void displayMetaData(){

        try
        {
            DatabaseMetaData dbmd=con.getMetaData();

            System.out.println("Driver Name: "+dbmd.getDriverName());
            System.out.println("Driver Version: "+dbmd.getDriverVersion());
            System.out.println("UserName: "+dbmd.getUserName());
            System.out.println("Database Product Name: "+dbmd.getDatabaseProductName());
            System.out.println("Database Product Version: "+dbmd.getDatabaseProductVersion());
            String table[]={"TABLE"};
            ResultSet rs=dbmd.getTables(null,null,null,table);

            System.out.println("Details zu den Tabellen");
            while(rs.next()){
                String tableName = rs.getString(3);
                System.out.println();
                System.out.println("Tabelle: " + tableName);
                ResultSet rsMeta = con.createStatement().executeQuery( "SELECT * FROM " + tableName );
                ResultSetMetaData meta = rsMeta.getMetaData();

                int numerics = 0;

                for ( int i = 1; i <= meta.getColumnCount(); i++ )
                {
                    System.out.printf( "%-20s %-20s%n", meta.getColumnLabel( i ),
                            meta.getColumnTypeName( i ) );

                    if ( meta.isSigned( i ) )
                        numerics++;
                }


                System.out.println( "Spalten: " + meta.getColumnCount() +
                        ", Numerisch: " + numerics );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
