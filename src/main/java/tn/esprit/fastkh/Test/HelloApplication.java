package tn.esprit.fastkh.Test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {

        try{
            //Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/AjouterBonsPlans.fxml")));
            Parent root2 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/AfficherBonsPlans.fxml")));
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Home.fxml")));


            Scene scene = new Scene(root);
            primaryStage.setTitle("Ajouter Bons Plans !");
            primaryStage.setScene(scene);
            primaryStage.show();


//           Scene scene1 = new Scene(root2);
//           primaryStage.setTitle("Afficher Bons Plans !");
//           primaryStage.setScene(scene1);
//           primaryStage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        launch();
    }
}