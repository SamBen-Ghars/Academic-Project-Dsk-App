package tn.esprit.fastkh.models;

public class Avis {



    private int Notes;
    private String commentaire ;


    public Avis(){}

    public Avis(int notes, String commentaire) {
        Notes = notes;
        this.commentaire = commentaire;
    }

    public int getNotes() {
        return Notes;
    }

    public void setNotes(int notes) {
        Notes = notes;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public String toString() {
        return "Avis{" +
                "Notes=" + Notes +
                ", commentaire='" + commentaire + '\'' +
                '}';
    }
}




