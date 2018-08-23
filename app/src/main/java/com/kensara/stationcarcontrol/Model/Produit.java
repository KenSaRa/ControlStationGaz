package com.kensara.stationcarcontrol.Model;

import java.util.Date;

public class Produit {

    String NomProduit;
    String typeProduit;
    double quantite;
    double prix;
    String TypePaiment;
    Date Datecreated;
    Employe employeAdded;


    public String getNomProduit() {
        return NomProduit;
    }

    public void setNomProduit(String nomProduit) {
        NomProduit = nomProduit;
    }

    public String getTypeProduit() {
        return typeProduit;
    }

    public void setTypeProduit(String typeProduit) {
        this.typeProduit = typeProduit;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getTypePaiment() {
        return TypePaiment;
    }

    public void setTypePaiment(String typePaiment) {
        TypePaiment = typePaiment;
    }

    public Date getDatecreated() {
        return Datecreated;
    }

    public void setDatecreated(Date datecreated) {
        Datecreated = datecreated;
    }
}
