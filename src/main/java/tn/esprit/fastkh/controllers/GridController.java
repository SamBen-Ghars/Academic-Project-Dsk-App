package tn.esprit.fastkh.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import tn.esprit.fastkh.models.BonsPlans;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class GridController {


    @FXML
    private Button Butmaps;

    @FXML
    private ImageView ImageAvis;

    @FXML
    private ImageView Imagev;

    @FXML
    private Text descp;

    @FXML
    private Label titlep;




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


    @FXML
    private Text addressp; // Assuming you have a Text or Label for the address

    @FXML
    private void initialize() {
        Butmaps.setOnAction(event -> openGoogleMaps());
    }

    private void openGoogleMaps() {
        String title = titlep.getText();
      //  String address = addressp.getText(); // Assuming addressp is the Text or Label for the address
        String query = title;   // + " " + address
        String url = "https://www.google.com/maps/search/?api=1&query=" + query.replace(" ", "+");

        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Desktop is not supported. Cannot open the browser.");
        }
    }


}
