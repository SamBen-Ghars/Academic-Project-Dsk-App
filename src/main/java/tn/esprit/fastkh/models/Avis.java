package tn.esprit.fastkh.models;

public class Avis {

    private int id;
    private int userId;
    private int bonsPlanId;
    private int notes;
    private String commentaire;

    public Avis() {}

    public Avis(int userId, int bonsPlanId, int notes, String commentaire) {
        this.userId = userId;
        this.bonsPlanId = bonsPlanId;
        this.notes = notes;
        this.commentaire = commentaire;
    }

    public Avis(int id, int userId, int bonsPlanId, int notes, String commentaire) {
        this.id = id;
        this.userId = userId;
        this.bonsPlanId = bonsPlanId;
        this.notes = notes;
        this.commentaire = commentaire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBonsPlanId() {
        return bonsPlanId;
    }

    public void setBonsPlanId(int bonsPlanId) {
        this.bonsPlanId = bonsPlanId;
    }

    public int getNotes() {
        return notes;
    }

    public void setNotes(int notes) {
        this.notes = notes;
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
                "id=" + id +
                ", userId=" + userId +
                ", bonsPlanId=" + bonsPlanId +
                ", notes=" + notes +
                ", commentaire='" + commentaire + '\'' +
                '}';
    }
}
