
package tn.esprit.fastkh.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import tn.esprit.fastkh.models.BonsPlans;
import tn.esprit.fastkh.models.User;
import tn.esprit.fastkh.services.BonsPlansService;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class AfficherBonsPlansController {


        private Stage primaryStage;
        private Scene scene;

        private Parent root;
        @FXML
        private Button Minimise;

        @FXML
        private HBox cardlayout;


        @FXML
        private Button close;

        @FXML
        private WebView mapWebView;


        @FXML
        private GridPane gridPane;
        private List<BonsPlans> recentlyAdded;

        private List<BonsPlans> recommendedPlans;


        private List<BonsPlans> bonsPlans;


        @FXML
        private AnchorPane avisPane;

        private int bonsPlanId;
        private User currentUser;

        public void SwitchHomepage(ActionEvent actionEvent) throws IOException {

                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Home.fxml")));
                primaryStage =(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
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



        private BonsPlansService bonsPlansService = new BonsPlansService();

        public void initialize() {
                try {
                        recentlyAdded = bonsPlansService.fetchRecentlyAddedBonsPlans();
                        recommendedPlans = bonsPlansService.getAll(); // Assuming this method fetches recommended plans

                        // Ajout des éléments dans le VBox (recentlyAdded)
                        for (BonsPlans plan : recentlyAdded) {
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CardLayoout.fxml"));
                                HBox cardBox = fxmlLoader.load();

                                CardController cardController = fxmlLoader.getController();
                                cardController.setData(plan, currentUser); // Pass both plan and user
                                cardlayout.getChildren().add(cardBox);
                        }

                        // Ajout des éléments dans le GridPane (recommendedPlans)
                        int row = 2;
                        int column = 0;
                        for (BonsPlans Bons : recommendedPlans) {
                                // Ajout des éléments dans le GridPane (recommendedPlans)
                                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CardLayout2.fxml"));
                                        VBox cardBox = fxmlLoader.load();
                                        GridController gridController = fxmlLoader.getController();
                                        gridController.setData(Bons, currentUser); // Pass both Bons and user

                                if (column == 3) { // Suppose 3 colonnes par ligne
                                        column = 0;
                                        row++;
                                }

                                gridPane.add(cardBox, column++, row);
                                GridPane.setMargin(cardBox, new Insets(10));
                        }

                        loadmap(recentlyAdded);

                } catch (IOException e) {
                        e.printStackTrace();
                }
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



}







