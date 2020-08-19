package com.hans;

public class Main {

    public static void main(String[] args) {
        DbHelper myHelper=new DbHelper();
        myHelper.OpenDatabase();
	System.out.println("Hello Database");
    }
}
