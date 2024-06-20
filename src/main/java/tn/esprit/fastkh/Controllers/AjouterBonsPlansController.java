package tn.esprit.fastkh.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.fastkh.Models.BonsPlans;
import tn.esprit.fastkh.Services.BonsPlansService;

import java.io.IOException;
import java.sql.SQLException;
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


    public void SwitchHomepage(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Home.fxml")));
        primaryStage =(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

}




