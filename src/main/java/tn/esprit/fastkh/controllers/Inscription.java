package tn.esprit.fastkh.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import tn.esprit.fastkh.models.User;
import tn.esprit.fastkh.services.UserService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class Inscription {

    @FXML
    private RadioButton Apprenti;

    @FXML
    private RadioButton chef;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private Button creation;

    @FXML
    private TextField email;

    @FXML
    private TextField nom;

    @FXML
    private PasswordField password;

    @FXML
    private TextField prenom;

    @FXML
    private Button retourLogin;

    @FXML
    private TextField phoneNum;

    private ToggleGroup toggleGroup;

    @FXML
    public void initialize() {
        toggleGroup = new ToggleGroup();
        Apprenti.setToggleGroup(toggleGroup);
        chef.setToggleGroup(toggleGroup);
        // Ensure one is selected by default if needed
        Apprenti.setSelected(true);
    }

    @FXML
    void ajoutUser(ActionEvent event) {

        RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
        String statusCompte = selectedRadioButton.getText().toLowerCase();

        User user = new User();
        user.setNom(nom.getText());
        user.setPrenom(prenom.getText());
        user.setEmail(email.getText());
        user.setPassword(password.getText());
        user.setConfirmPassword(confirmPassword.getText());

        // Validate and parse phone number
        String phoneText = phoneNum.getText().trim();
        if (phoneText.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Numéro de téléphone obligatoire");
            return;
        }

        int phoneNumber;
        try {
            phoneNumber = Integer.parseInt(phoneText);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Numéro de téléphone invalide. Veuillez entrer un nombre valide.");
            return;
        }

        user.setPhoneNum(phoneNumber);
        user.setStatusCompte(statusCompte);

        if (!password.getText().equals(confirmPassword.getText())) {
            showAlert(Alert.AlertType.ERROR, "Error", "Les mots de passe ne sont pas identiques");
            return;
        }

        if (user.getNom().isEmpty() || user.getPrenom().isEmpty() || user.getEmail().isEmpty() || user.getPassword().isEmpty() || user.getConfirmPassword().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Champ obligatoire manquant");
            return;
        }

        UserService userService = new UserService();
        try {
            userService.ajouter(user);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Utilisateur ajoutée \n Si votre numéro n'est pas valide \n notre équipe vas vous contacté par email \n pour un numéro de téléphone valide");
            String fxmlFile = Apprenti.isSelected() ? "/login.fxml" : "/chefPhone.fxml";
            loadFXML(fxmlFile, event);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Utilisateur non ajoutée");
            e.printStackTrace();
        }
    }

    @FXML
    void returnToLogin(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/welcome.fxml")));
            Stage stage = (Stage) retourLogin.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFXML(String fxmlFile, ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }


//    private boolean verifyPhoneNumber(String phoneNum) {
//        String myURI = "https://api.bulksms.com/v1/messages";
//        String myUsername = "khalil1";
//        String myPassword = "93936500Azerty";
//        String messageBody = "Dobrá práce! Jak se máš?";
//
//        JSONObject json = new JSONObject();
//        json.put("to", phoneNum);
//        json.put("encoding", "UNICODE");
//        json.put("body", messageBody);
//
//        String myData = json.toString();
//
//        URL url = null;
//        try {
//            url = new URL(myURI);
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }
//        HttpURLConnection request = null;
//        try {
//            request = (HttpURLConnection) url.openConnection();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        request.setDoOutput(true);
//
//        String authStr = myUsername + ":" + myPassword;
//        String authEncoded = Base64.getEncoder().encodeToString(authStr.getBytes());
//        request.setRequestProperty("Authorization", "Basic " + authEncoded);
//
//        try {
//            request.setRequestMethod("POST");
//        } catch (ProtocolException e) {
//            throw new RuntimeException(e);
//        }
//        request.setRequestProperty("Content-Type", "application/json");
//
//        OutputStreamWriter out = null;
//        try {
//            out = new OutputStreamWriter(request.getOutputStream(), StandardCharsets.UTF_8);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            out.write(myData);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            out.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        try {
//            InputStream response = request.getInputStream();
//            BufferedReader in = new BufferedReader(new InputStreamReader(response));
//            String replyText;
//            while ((replyText = in.readLine())!= null) {
//                System.out.println(replyText);
//            }
//            in.close();
//        } catch (IOException ex) {
//            System.out.println("An error occurred:" + ex.getMessage());
//            BufferedReader in = new BufferedReader(new InputStreamReader(request.getErrorStream()));
//            String replyText;
//            while (true) {
//                try {
//                    if (!((replyText = in.readLine())!= null)) break;
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//                System.out.println(replyText);
//            }
//            try {
//                in.close();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        request.disconnect();
//        return false;
//    }
}
