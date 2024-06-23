package tn.esprit.fastkh.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;




public class MoudifierBonsPlansController {

    private Stage primaryStage;
    private Scene scene;
    private Parent root ;

    public void SwitchHomepage(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Home.fxml")));
        primaryStage =(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
