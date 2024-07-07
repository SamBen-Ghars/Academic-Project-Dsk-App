package tn.esprit.fastkh.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Welcome {




    @FXML
    private Button connexionButton;

    @FXML
    private Button inscriptionButton;


    @FXML
    void inscriptionUser(ActionEvent event) {

        try {
            System.out.println(getClass().getResource("/inscription.fxml"));
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/inscription.fxml")));
            Stage stage = (Stage) inscriptionButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void loginUser(ActionEvent event) {
        try {
            System.out.println(getClass().getResource("/login.fxml"));
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/login.fxml")));
            Stage stage = (Stage) connexionButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
