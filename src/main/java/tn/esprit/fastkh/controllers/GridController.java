package tn.esprit.fastkh.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tn.esprit.fastkh.models.BonsPlans;
import tn.esprit.fastkh.models.User;

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

    private User currentUser; // Assurez-vous que cela est défini lors de la connexion de l'utilisateur
    private BonsPlans selectedBonsPlans; // Cela doit être défini lors de la sélection
    private int bonsPlanId;
    private int  userId;

    public void setData(BonsPlans plan, User currentUser) {
        this.selectedBonsPlans = plan;
        this.currentUser = currentUser; // Ensure currentUser is properly assigned
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
        // String address = addressp.getText(); // Assuming addressp is the Text or Label for the address
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


    @FXML
    private void openAvisView(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AvisView.fxml"));
            Parent avisView = loader.load();

            AvisController avisController = loader.getController();
            BonsPlans selectedBonsPlans = new BonsPlans();
            avisController.setBonsPlanId(selectedBonsPlans.getId());
            User currentUser = new User();
            avisController.setUserId(currentUser.getId());

            Stage stage = new Stage();
            stage.setScene(new Scene(avisView));
            stage.setTitle("Donner un avis");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

}
