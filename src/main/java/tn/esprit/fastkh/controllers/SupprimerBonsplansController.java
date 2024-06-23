package tn.esprit.fastkh.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.fastkh.models.BonsPlans;
import tn.esprit.fastkh.services.BonsPlansService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class SupprimerBonsplansController {

    private Stage primaryStage;
    private Scene scene;
    private Parent root ;

    @FXML
    private TextField fxid;


    @FXML
    void deleteBonsPlans(ActionEvent event) throws SQLException {
        BonsPlansService BP = new BonsPlansService();
        BonsPlans B = new BonsPlans();
        B.setId(Integer.parseInt(fxid.getText()));
        BP.delete(B);
        Alert alert = new Alert((Alert.AlertType.INFORMATION));
        alert.show();
        alert.setTitle("Quiz Deleted Succesfully !");


    }

    public void SwitchHomepage(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Home.fxml")));
        primaryStage =(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
