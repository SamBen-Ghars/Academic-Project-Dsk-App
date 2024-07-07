package tn.esprit.fastkh.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {

        try{


           // Ce route est utilser  pour c la page d'acceuil
            // qui va nous permettre a utilser d'autre route a l'aide du controller
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/login.fxml")));

           //  creation d'une scene de type scene qui utlise le rout home page
            Scene scene = new Scene(root);
            primaryStage.setTitle("Home page Bons Plans !");
            primaryStage.setScene(scene);
            primaryStage.show();


            Image icon = new Image("image/logo.jpg");
            primaryStage.getIcons().add(icon);





        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        launch();
    }
}