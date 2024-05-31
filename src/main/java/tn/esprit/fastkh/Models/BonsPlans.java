package tn.esprit.fastkh.Models;

public class BonsPlans {


    private int id ;
    private String titre , discription , addresse;



    public BonsPlans(){}

    public BonsPlans(int id, String titre, String discription, String addresse) {
        this.id = id;
        this.titre = titre;
        this.discription = discription;
        this.addresse = addresse;
    }

    public BonsPlans(String titre, String discription, String addresse) {
        this.titre = titre;
        this.discription = discription;
        this.addresse = addresse;
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getDiscription() {
        return discription;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    @Override
    public String toString() {
        return "BonsPlans{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", discription='" + discription + '\'' +
                ", addresse='" + addresse + '\'' +
                '}';
    }
}
