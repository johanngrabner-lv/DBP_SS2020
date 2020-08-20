package com.hans;

public class Main {

    public static void main(String[] args) {
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

        myDbHelper.addProdukt("Handy",100.40);
    }
}
