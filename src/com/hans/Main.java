package com.hans;

public class Main {

    public static void main(String[] args) {
        DbHelper myDbHelper=new DbHelper();
        myDbHelper.OpenDatabase();
	    System.out.println("Hello JDBC");
    }
}
