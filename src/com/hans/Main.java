package com.hans;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        PersonsAndOrdersHelper h =new PersonsAndOrdersHelper();
        h.OpenDatabase();
        h.ForeignKeyDemo();
    }

    public static void Woche1(){
        /*
   DbHelper myDbHelper=new DbHelper();
   myDbHelper.OpenDatabase();
   //myDbHelper.CreateKundenTable();
   //myDbHelper.ReadKunden();
//System.out.println("Hello JDBC");

boolean existiert = myDbHelper.tableExists("Kunden");
System.out.println(existiert);
existiert = myDbHelper.tableExists("Produkte");
   System.out.println(existiert);

   String ddl="(Produktbezeichnung varchar(20), Preis real)";
   myDbHelper.createTable("Produkte", ddl);

  // myDbHelper.addProdukt("Computer",1500.00);
// myDbHelper.addProduktPreparedStatement("Computer",1500.00);


   //1.Aufgabe
   Produkt p1 =new Produkt();
   p1.setProduktbezeichnung("Drucker HP");
   p1.setPreis(200.20);
   //myDbHelper.addProdukt(p1);
   //myDbHelper.readAllProdukte();
   /*
   //2. Aufgabe */
        List<Produkt> alleProdukte = myDbHelper.getAllProdukte();

        System.out.println(alleProdukte);
        //rowid, Bezeichnung, Preis



   */

    }
}
