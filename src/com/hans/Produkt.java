package com.hans;

public class Produkt {
    private int rowid;
    private String Produktbezeichnung;
    private double Preis;

    public int getRowid() {
        return rowid;
    }

    public void setRowid(int rowid) {
        this.rowid = rowid;
    }

    public String getProduktbezeichnung() {
        return Produktbezeichnung;
    }

    public void setProduktbezeichnung(String produktbezeichnung) {
        Produktbezeichnung = produktbezeichnung;
    }

    public double getPreis() {
        return Preis;
    }

    public void setPreis(double preis) {
        Preis = preis;
    }

    @Override
    public String toString() {
        return "Produkt{" +
                "rowid=" + rowid +
                ", Produktbezeichnung='" + Produktbezeichnung + '\'' +
                ", Preis=" + Preis +
                '}';
    }
}
