package com.hans;

public class Main {

    public static void main(String[] args) {
        DbHelper myDbHelper=new DbHelper();
        myDbHelper.OpenDatabase();
        myDbHelper.CreateKundenTable();
        myDbHelper.ReadKunden();
	    System.out.println("Hello JDBC");
    }
}
