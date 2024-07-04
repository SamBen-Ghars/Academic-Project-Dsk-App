package tn.esprit.fastkh.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HomeController {


    private Stage primaryStage ;
    private Scene scene ;
    private Parent root ;









    public void SwitchtoaddBonsplan(javafx.event.ActionEvent actionEvent) throws IOException {


        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/AjouterBonsPlans.fxml")));
        primaryStage =(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void SwitchafficherBonsplans(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/AfficherBonsPlans.fxml")));
        primaryStage =(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public void SwitchHomepage(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/AfficherBonsPlans.fxml")));
        primaryStage =(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public void SwitchwithModifier(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/MoudifierBonsPlans.fxml")));
        primaryStage =(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void SwitchwithSupprimer(ActionEvent actionEvent) throws IOException {


        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/SupprimerBonsPlans.fxml")));
        primaryStage =(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

