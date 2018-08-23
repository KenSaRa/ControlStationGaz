package com.kensara.stationcarcontrol.Model;

public class VenteProduit {

    Employe pompiste;
    String numeroPompe;
    Produit produit;

    public Employe getPompiste() {
        return pompiste;
    }

    public void setPompiste(Employe pompiste) {
        this.pompiste = pompiste;
    }

    public String getNumeroPompe() {
        return numeroPompe;
    }

    public void setNumeroPompe(String numeroPompe) {
        this.numeroPompe = numeroPompe;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
}
