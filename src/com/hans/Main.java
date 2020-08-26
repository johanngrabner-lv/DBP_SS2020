package com.hans;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        klausurVorbereitung kv =new klausurVorbereitung();
        System.out.println("Hello Campus02");
        kv.init();
        System.out.println("init was successful");
        int affected=0;
      // affected =  kv.dropKundenTable();
        System.out.println("Result from drop " + affected);
      // affected = kv.createKundenTable();
        System.out.println("Result from create " + affected);
      //  affected = kv.createRechnungenTable();
        System.out.println("Result from create rechnungen " + affected);
        kv.displayMetaData();


        int newIdValue=0;

        /*
        Kunde k=new Kunde();
        k.setKdnr(-1);
        k.setVorname("Johann");
        k.setNachname("Grabner");
        k.setGeschlecht("xxx");
        k.setBonuspunkte(300);
         newIdValue = kv.insertKundeAndSetNewId(k);
        k.setKdnr(newIdValue);*/






        Kunde k1=new Kunde();
        k1.setKdnr(1);
        k1.setVorname("Johann");
        k1.setNachname("Grabner");
        k1.setGeschlecht("Mann");
        k1.setBonuspunkte(200);

        Kunde k2=new Kunde();
        k2.setKdnr(3);
        k2.setVorname("Kerstin");
        k2.setNachname("Müller");
        k2.setGeschlecht("Frau");
        k2.setBonuspunkte(300);

        Rechnung rNeu =new Rechnung();
        rNeu.setRenr(-1); //Readability
        rNeu.setDatum("1.1.2020");
        rNeu.setGesamtbetrag(4000);

        //kv.dropRechnungenTable();
       // kv.createRechnungenTable();

       // newIdValue = kv.insertRechnungAndSetNewId(rNeu,k2);

        Kunde kSuche = kv.getKundeByKdnr(1);
        System.out.println(kSuche);

        kSuche.setVorname("Karli");
       // kv.updateKunde(kSuche);

        System.out.println("Alle Kunden");
        List<Kunde> alleKunden = kv.getAlleKunden();
        System.out.println(alleKunden);

        System.out.println("Alle weiblichen Kunden");
        List<Kunde> alleWeiblicheKunden = kv.getAlleWeiblichenKundenInnen();
        System.out.println(alleWeiblicheKunden);

        Kunde kAufgabe7=new Kunde();
        kAufgabe7.setKdnr(3);
        kAufgabe7.setVorname("Britta");
        kAufgabe7.setNachname("Maier");
        kAufgabe7.setGeschlecht("Frau");
        kAufgabe7.setBonuspunkte(400);

        Rechnung rNeu1 =new Rechnung();
        rNeu1.setRenr(-1); //Readability
        rNeu1.setDatum("1.5.2020");
        rNeu1.setGesamtbetrag(5000);

        ArrayList<Rechnung> rechnungen =new ArrayList<Rechnung>();
        rechnungen.add(rNeu1);

        kv.insertKundeUndRechnungen(rechnungen,kAufgabe7);
        System.out.println("Vor Änderung");
        System.out.println(rNeu1);
        rNeu1.setGesamtbetrag(7000);
        kv.updateRechnung(rNeu1);
        System.out.println("Nach Änderung");

        Rechnung geaenderteRechnung = kv.getRechnungByRenr(rNeu1.getRenr());

        Kunde kundeNachladen = kv.getKundeByKdnr(geaenderteRechnung.getKdnr());
        geaenderteRechnung.kunde=kundeNachladen;
        System.out.println(geaenderteRechnung);

        kv.loescheAlleRechnungenUndDanachDenKunden(kundeNachladen);

        System.out.println("Alle Kunden nach löschen");
        List<Kunde> alleKundenNachLoeschen = kv.getAlleKunden();
        System.out.println(alleKundenNachLoeschen);
    }

    public static void Woche2()
    {

        PersonsAndOrdersHelper h =new PersonsAndOrdersHelper();
        h.OpenDatabase();
        // h.ForeignKeyDemo();
        if (h.tableExists("Personen")==false){
            System.out.println("Tabelle ist noch nicht vorhanden");
            h.Demo1DDLStatementCreatePersonen();
        }
        else
        {
            System.out.println("Tabelle vorhanden");
        }
        System.out.println("Alle Tabellen anzeigen");
        h.ReadAllTables();
        System.out.println("Person hinzufügen");
        //  h.Demo2DMLInsertPersonen("Verena",12);

        h.ReadAllPersonen();
        System.out.println("MetaData");
        //h.DisplayMetaData();
        h.readTablesKerstin();

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
   //2. Aufgabe
        List<Produkt> alleProdukte = myDbHelper.getAllProdukte();

        System.out.println(alleProdukte);
        */

        //rowid, Bezeichnung, Preis



    }
}
