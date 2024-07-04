package tn.esprit.fastkh.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.esprit.fastkh.models.BonsPlans;
import tn.esprit.fastkh.services.BonsPlansService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class GererBonsPlansController {


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
    private Button browseButton;

    @FXML
    private TableColumn<?, ?> adresseT;

    @FXML
    private TableColumn<?, ?> IdT;

    @FXML
    private TableColumn<?, ?> descriptionT;

    @FXML
    private TableColumn<BonsPlans, ImageView> imageT;


    @FXML
    private TableColumn<?, ?> titreT;

    @FXML
    private TableView<BonsPlans> tableV;

    private String imagePath;

    private byte[] imageBytes;


// related to search

    @FXML
    private TextField searchField;

    @FXML
    private Button searchButton; // The Button to trigger the search

// related to delete

    @FXML
    private TextField fxid;
    @FXML
    void handleAjouterBonsPlans(ActionEvent event) {
        if (fxTitle.getText().isEmpty() || fxDescription.getText().isEmpty() || fxAdresse.getText().isEmpty() || imageBytes == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs et sélectionner une image avant d'ajouter un bon plan.");
            alert.showAndWait();
            return;
        }

        BonsPlansService BP = new BonsPlansService();
        BonsPlans B = new BonsPlans();
        B.setTitle(fxTitle.getText());
        B.setDescription(fxDescription.getText());
        B.setAdresse(fxAdresse.getText());
        B.setImage(imageBytes); // Set the image byte array in the BonsPlans object
        BP.add(B);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("Votre Bon Plan a été ajouté avec succès");
        alert.showAndWait();
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
                imageView.setImage(image);
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);
                imageBytes = Files.readAllBytes(selectedFile.toPath()); // Read image file into byte array
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void handleAfficherBonsPlans(ActionEvent event) {
        BonsPlansService bonsPlansService = new BonsPlansService();
        List<BonsPlans> bonsPlansList = bonsPlansService.getAll();
        ObservableList<BonsPlans> observableList = FXCollections.observableList(bonsPlansList);
        tableV.setItems(observableList);


        IdT.setCellValueFactory(new PropertyValueFactory<>("id"));
        titreT.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionT.setCellValueFactory(new PropertyValueFactory<>("description"));
        adresseT.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        imageT.setCellValueFactory(new PropertyValueFactory<>("imageView"));
    }



    @FXML
    void handleClearFieldsAction(ActionEvent event) {

        clearFields();
    }

    private void clearFields() {
        fxid.clear();
        fxTitle.clear();
        fxDescription.clear();
        fxAdresse.clear();
        imageView.setImage(null);
        imageBytes = null;
    }



    private BonsPlansService bonsPlansService = new BonsPlansService(); // Instantiate BonsPlansService


    @FXML
    public void initialize() {
        IdT.setCellValueFactory(new PropertyValueFactory<>("id"));
        titreT.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionT.setCellValueFactory(new PropertyValueFactory<>("description"));
        adresseT.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        imageT.setCellValueFactory(new PropertyValueFactory<>("imageView"));
    }
    @FXML
    void handleSearchAction(ActionEvent event) {
        String searchText = searchField.getText();
        if (searchText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer un texte de recherche.");
            alert.showAndWait();
            return;
        }

        List<BonsPlans> searchResults = bonsPlansService.searchBonsPlans(searchText.toLowerCase());
        displaySearchResults(searchResults);
    }

        private void displaySearchResults(List<BonsPlans> searchResults) {
            tableV.getItems().clear();
            if (searchResults.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Résultats de recherche");
                alert.setHeaderText(null);
                alert.setContentText("Aucun Bon Plan trouvé.");
                alert.showAndWait();
            } else {
                tableV.getItems().addAll(searchResults);
            }
    }



    @FXML
    void deleteBonsPlans(ActionEvent event) throws SQLException {
        BonsPlansService BP = new BonsPlansService();
        BonsPlans B = new BonsPlans();
        B.setId(Integer.parseInt(fxid.getText()));
        BP.delete(B);
        Alert alert = new Alert((Alert.AlertType.INFORMATION));
        alert.show();
        alert.setTitle("Bons Plans  Deleted Succesfully !");


    }



    @FXML
    void ClearFieldsActiondelete(ActionEvent event) {
        clearFields2();
    }

    private void clearFields2() {
        fxid.clear();

    }

    public void SwitchafficherBonsplans(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/AfficherBonsPlans.fxml")));
        primaryStage =(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }








}




