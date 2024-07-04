package tn.esprit.fastkh.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import tn.esprit.fastkh.models.BonsPlans;

import java.io.ByteArrayInputStream;

public class CardController {



    @FXML
    private Button Butmaps;

    @FXML
    private ImageView Imagev;

    @FXML
    private ImageView ImageAvis;


    @FXML
    private Text descp;

    @FXML
    private Label titlep;

    @FXML
    private HBox box;


    public HBox getBox() {
        return box;
    }
    private String[] colors = {"B9E5FF", "BDB2FE", "FB9AA8", "FF5056"};

    public void setData(BonsPlans plan) {
        if (plan != null && plan.getImage() != null && plan.getImage().length > 0) {
            try {
                ByteArrayInputStream bis = new ByteArrayInputStream(plan.getImage());
                Image image = new Image(bis);
                Imagev.setImage(image);
                descp.setText(plan.getDescription());
                titlep.setText(plan.getTitle());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Error: Plan or its image data is null.");
        }
    }




}

