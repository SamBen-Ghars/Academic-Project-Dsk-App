package tn.esprit.fastkh.Models;

import java.util.ArrayList;
import java.util.List;

public class Recette {



    private int id;
    private String nom;
    private List<Ingredient> ingredients;


    public Recette(int id, String nom) {
        this.id = id;
        this.nom = nom;
        this.ingredients = new ArrayList<>();
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }


}
