package tn.esprit.fastkh.controllers;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tn.esprit.fastkh.models.User;
import tn.esprit.fastkh.services.UserService;

import java.io.IOException;
import java.util.Optional;


public class Menu {

    @FXML
    private Button bonPlan;

    @FXML
    private Button eventsButton;

    @FXML
    private Button formusButton;

    @FXML
    private Button quizButton;

    @FXML
    private Button recetteButton;

    @FXML
    private Button tutoButton;

    @FXML
    private Text userWelcome;

    @FXML
    private TextField userName;

    @FXML
    private ImageView logoutImage;


    @FXML
    void displayBonPlan(ActionEvent event) {

    }

    @FXML
    void displayEvents(ActionEvent event) {

    }

    @FXML
    void displayForums(ActionEvent event) {

    }

    @FXML
    void displayQuiz(ActionEvent event) {

    }

    @FXML
    void displayRecette(ActionEvent event) {

    }

    @FXML
    void displayTuto(ActionEvent event) {

    }

    private int userId; // Store the user ID here
    private String nom;
    private String prenom;

    public void initData(int userId) {
        this.userId = userId;
        System.out.println("User ID received in MenuController: " + userId);

        // Fetch nom and prenom from the database based on userId
        fetchNomPrenom();
    }
    private void fetchNomPrenom() {
        // Assuming you have a UserService or similar to fetch user details
        UserService userService = new UserService();
        User user = userService.findById(userId); // Implement this method to fetch user details by userId

        if (user != null) {
            nom = user.getNom();
            prenom = user.getPrenom();
            System.out.println( nom + " " + prenom);
            userName.setText(nom + " " + prenom);
        } else {
            System.out.println("User not found for ID: " + userId);
        }
    }

    @FXML
    void displayName(ActionEvent event) {

    }

    @FXML
    void logout(MouseEvent event) {
        // Create a confirmation dialog
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Déconnexion");
        alert.setContentText("Êtes-vous sûr de vouloir vous déconnecter ?");

        // Add "oui" and "non" buttons to the dialog
        ButtonType ouiButton = new ButtonType("Oui");
        ButtonType nonButton = new ButtonType("Non");
        alert.getButtonTypes().setAll(ouiButton, nonButton);

        // Show the dialog and wait for a user response
        Optional<ButtonType> result = alert.showAndWait();

        // Handle user response
        if (result.isPresent() && result.get() == ouiButton) {
            // User clicked "Oui", navigate to login interface
            navigateToLogin();
        }
    }

    private void navigateToLogin() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
            Stage stage = (Stage) userWelcome.getScene().getWindow(); // Assuming userWelcome is a node in your current scene
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
