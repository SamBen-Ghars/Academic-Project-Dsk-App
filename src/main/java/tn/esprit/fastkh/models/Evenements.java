package tn.esprit.fastkh.models;

import java.util.Date;

public class Evenements {
    private int idevents ;

    private String titre , discription;

    private Date Date ;

    private  String lieu ;

    public Evenements (){}

    public Evenements(int idevents, String titre, String discription, java.util.Date date, String lieu) {
        this.idevents = idevents;
        this.titre = titre;
        this.discription = discription;
        Date = date;
        this.lieu = lieu;
    }

    public Evenements(String titre, String discription, java.util.Date date, String lieu) {
        this.titre = titre;
        this.discription = discription;
        Date = date;
        this.lieu = lieu;
    }


    public int getIdevents() {
        return idevents;
    }

    public void setIdevents(int idevents) {
        this.idevents = idevents;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    @Override
    public String toString() {
        return "Evenements{" +
                "idevents=" + idevents +
                ", titre='" + titre + '\'' +
                ", discription='" + discription + '\'' +
                ", Date=" + Date +
                ", lieu='" + lieu + '\'' +
                '}';
    }


}
