package com.kensara.stationcarcontrol.Model;

public class Employe {

    String nom;
    String prenom;
    String typeEmploi;
    String telephone;
    String age;
    Pompe idPompe;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTypeEmploi() {
        return typeEmploi;
    }

    public void setTypeEmploi(String typeEmploi) {
        this.typeEmploi = typeEmploi;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Pompe getIdPompe() {
        return idPompe;
    }

    public void setIdPompe(Pompe idPompe) {
        this.idPompe = idPompe;
    }

    @Override
    public String toString() {
        return nom + " " + prenom;
    }
}
