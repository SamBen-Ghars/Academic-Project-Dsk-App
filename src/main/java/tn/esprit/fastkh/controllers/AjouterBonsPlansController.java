package tn.esprit.fastkh.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.esprit.fastkh.models.BonsPlans;
import tn.esprit.fastkh.services.BonsPlansService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;


public class AjouterBonsPlansController {


    private Scene scene;
    private Stage primaryStage;
    private Parent root ;

    @FXML
    private TextField fxAdresse;

    @FXML
    private TextField fxDescription;

    @FXML
    private TextField fxTitle;

    @FXML
    private ImageView imageView;

    @FXML
    void handleAjouterBonsPlans(ActionEvent event) {

        BonsPlansService BP = new BonsPlansService();
        BonsPlans B = new BonsPlans();
        B.setTitle(fxTitle.getText());
        B.setDescription(fxDescription.getText());
        B.setAdresse(fxAdresse.getText());
        BP.add(B);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.show();
        alert.setTitle("Votre Bon Plans a été ajouter avec succés");
    }



    @FXML
    void handleBrowseButtonAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                FileInputStream fis = new FileInputStream(selectedFile);
                Image image = new Image(fis);
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);
                // Affichez l'image sélectionnée ou faites quelque chose d'autre avec elle.
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void SwitchHomepage(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Home.fxml")));
        primaryStage =(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();


    }


}




