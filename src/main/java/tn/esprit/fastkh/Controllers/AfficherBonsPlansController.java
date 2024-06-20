package tn.esprit.fastkh.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tn.esprit.fastkh.Models.BonsPlans;
import tn.esprit.fastkh.Services.BonsPlansService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class  AfficherBonsPlansController{


      private Stage primaryStage;
      private Scene scene;

      private Parent root;

        @FXML
        private TableColumn<?, ?> adresseT;

        @FXML
        private TableColumn<?, ?> descriptionT;

        @FXML
        private TableColumn<?, ?> imageT;

        @FXML
        private TableColumn<?, ?> titreT;

       @FXML
       private TableView<BonsPlans> tableV;

        @FXML
        void handleAfficherBonsPlans(ActionEvent event) {

            BonsPlansService bonsPlansService = new BonsPlansService();
            List<BonsPlans> bonsPlansList = bonsPlansService.getAll();
            ObservableList<BonsPlans> observableList = FXCollections.observableList(bonsPlansList);
            tableV.setItems(observableList);
            titreT.setCellValueFactory(new PropertyValueFactory<>("Title"));
            adresseT.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
            descriptionT.setCellValueFactory(new PropertyValueFactory<>("description"));
            imageT.setCellValueFactory(new PropertyValueFactory<>("image"));
        }

    public void SwitchHomepage(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Home.fxml")));
        primaryStage =(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}




