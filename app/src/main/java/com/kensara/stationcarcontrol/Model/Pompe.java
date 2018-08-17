package com.kensara.stationcarcontrol.Model;

public class Pompe {
    long idPompe;
    String nom;
    String adresse;
    double longitude;
    double latitude;

    public long getIdPompe() {
        return idPompe;
    }

    public void setIdPompe(long idPompe) {
        this.idPompe = idPompe;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
