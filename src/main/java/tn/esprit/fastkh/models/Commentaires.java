package tn.esprit.fastkh.models;

import java.util.Date;

public class Commentaires {

    private int idcommentaire ;
    private String contenucommentaire ;

    private Date DateCommentaire ;



    public Commentaires(){}

    public Commentaires(int idcommentaire, String contenucommentaire, Date dateCommentaire) {
        this.idcommentaire = idcommentaire;
        this.contenucommentaire = contenucommentaire;
        DateCommentaire = dateCommentaire;
    }

    public Commentaires(String contenucommentaire, Date dateCommentaire) {
        this.contenucommentaire = contenucommentaire;
        DateCommentaire = dateCommentaire;
    }

    public int getIdcommentaire() {
        return idcommentaire;
    }

    public void setIdcommentaire(int idcommentaire) {
        this.idcommentaire = idcommentaire;
    }

    public String getContenucommentaire() {
        return contenucommentaire;
    }

    public void setContenucommentaire(String contenucommentaire) {
        this.contenucommentaire = contenucommentaire;
    }

    public Date getDateCommentaire() {
        return DateCommentaire;
    }

    public void setDateCommentaire(Date dateCommentaire) {
        DateCommentaire = dateCommentaire;
    }


    @Override
    public String toString() {
        return "Commentaires{" +
                "idcommentaire=" + idcommentaire +
                ", contenucommentaire='" + contenucommentaire + '\'' +
                ", DateCommentaire=" + DateCommentaire +
                '}';
    }
}
