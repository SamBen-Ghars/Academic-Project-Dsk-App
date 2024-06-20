package tn.esprit.fastkh.Models;

public class Ingredient {

    private int id;
    private String nom;
    private int quantite;
    private int recetteId;

    public Ingredient(int id, String nom, int quantite, int recetteId) {
        this.id = id;
        this.nom = nom;
        this.quantite = quantite;
        this.recetteId = recetteId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getRecetteId() {
        return recetteId;
    }

    public void setRecetteId(int recetteId) {
        this.recetteId = recetteId;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", quantite=" + quantite +
                ", recetteId=" + recetteId +
                '}';
    }
}
