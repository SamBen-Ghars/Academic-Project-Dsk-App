package tn.esprit.fastkh.models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;

public class BonsPlans {


    private int id;
    private String title, description, adresse;

    private byte[] image;


    public BonsPlans() {
    }


    public BonsPlans(String title, String description, String adresse, byte[] image) {
        this.title = title;
        this.description = description;
        this.adresse = adresse;
        this.image = image;
    }

    public BonsPlans(int id, String title, String description, String adresse, byte[] image) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.adresse = adresse;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }



    public ImageView getImageView() {
        if (image != null) {
            ByteArrayInputStream bis = new ByteArrayInputStream(image);
            Image img = new Image(bis);
            ImageView imageView = new ImageView(img);
            imageView.setFitHeight(50); // Adjust as needed
            imageView.setFitWidth(50); // Adjust as needed
            return imageView;
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "BonsPlans{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", adresse='" + adresse + '\'' +
                //", image=" + Arrays.toString(image) +
                // ", image='" + imageString + '\'' +
                '}';
    }
}