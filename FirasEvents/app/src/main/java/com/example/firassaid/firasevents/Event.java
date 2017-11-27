package com.example.firassaid.firasevents;

import org.json.JSONObject;

import java.util.Date;

/**
 * Created by Firas Said on 25/11/2017.
 */

public class Event {
    private int id;
    private String Nom;
    private int Prix;

    private int Duree;
    private String Lieu;
    private int Date_Debut;
    private String Description;
    private String Type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public int getPrix() {
        return Prix;
    }

    public void setPrix(int prix) {
        Prix = prix;
    }

    public int getDuree() {
        return Duree;
    }

    public void setDuree(int duree) {
        Duree = duree;
    }

    public String getLieu() {
        return Lieu;
    }

    public void setLieu(String lieu) {
        Lieu = lieu;
    }

    public int getDate_Debut() {
        return Date_Debut;
    }

    public void setDate_Debut(int date_Debut) {
        Date_Debut = date_Debut;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
    public Event(JSONObject j) {
        this.id = j.optInt("id");
        this.Type = j.optString("type");
        this.Description = j.optString("description");
        this.Prix = j.optInt("prix");
        this.Nom = j.optString("nom");
        this.Duree = j.optInt("duree");
        this.Lieu = j.optString("lieu");
        this.Date_Debut = j.optInt("date_Debut");
    }
}
