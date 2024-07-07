package tn.esprit.fastkh.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import tn.esprit.fastkh.models.BonsPlans;
import tn.esprit.fastkh.models.User;
import tn.esprit.fastkh.services.BonsPlansService;
import tn.esprit.fastkh.services.UserService;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class HomeController {

    private Stage primaryStage;
    private Scene scene;
    private Parent root;


    private int userId; // Store the user ID here
    private String nom;
    private String prenom;

    @FXML
    private Text userWelcome;

    @FXML
    private WebView mapWebView;



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
            // userName.setText(nom + " " + prenom);
        } else {
            System.out.println("User not found for ID: " + userId);
        }
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

    private final BonsPlansService bonsPlansService = new BonsPlansService();

    @FXML
    public void initialize() {
        List<BonsPlans> recommendedPlans = bonsPlansService.getAll();
        loadmap(recommendedPlans);
    }

    public void switchToAddBonsPlan(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/AjouterBonsPlans.fxml")));
        primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void switchToAfficherBonsPlans(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/AfficherBonsPlans.fxml")));
        primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void SwitchHomepage(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Home.fxml")));
        primaryStage =(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();


    }


    public void switchToModify(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ModifierBonsPlans.fxml")));
        primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void switchToDelete(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/SupprimerBonsPlans.fxml")));
        primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }




    public void loadmap(List<BonsPlans> plans) {
        if (mapWebView != null) {
            WebEngine webEngine = mapWebView.getEngine();
            String apiKey = "AIzaSyBObMx0yIA7Z3BmCJ1-pEURuP1TrLtd7Y8";
            StringBuilder htmlContentBuilder = new StringBuilder();



            try {
                for (BonsPlans bons : plans) {
                    String address = bons.getAdresse();
                    String mapUrl = "https://www.google.com/maps/embed/v1/place?key=" + apiKey + "&q=" + address.replace(" ", "+");

                    String iframe = "<iframe width=\"800\" height=\"600\" frameborder=\"0\" style=\"border:0\" " +
                            "loading=\"lazy\" allowfullscreen referrerpolicy=\"no-referrer-when-downgrade\" " +
                            "src=\"" + mapUrl + "\"></iframe>";

                    htmlContentBuilder.append(iframe);
                }

                String htmlContent = htmlContentBuilder.toString();
                webEngine.loadContent(htmlContent, "text/html");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("mapWebView n'est pas initialisé dans le fichier FXML.");
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
