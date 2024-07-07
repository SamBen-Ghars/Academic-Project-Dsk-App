package tn.esprit.fastkh.controllers;


import com.google.gson.JsonObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
import tn.esprit.fastkh.models.User;
import tn.esprit.fastkh.services.UserService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;

public class Login {

    @FXML
    private TextField UserEmail;

    @FXML
    private PasswordField UserPassword;

    @FXML
    private Button inscriptionButton;

    @FXML
    private Button login;


    @FXML
    void LoginUser(ActionEvent event) {
        String email = UserEmail.getText().trim();
        String password = UserPassword.getText().trim();

        if (email.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Email and Password are required.");
            return;
        }

        UserService userService = new UserService();
        User user = userService.findByEmail(email);

        if (user == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Email not found.");
        } else {
            // Log raw and hashed passwords for debugging
            System.out.println("Raw password: " + password);
            System.out.println("Hashed password from DB: " + user.getPassword());

            if (user.getPassword() == null) { // User found in chefdemands
                showAlert(Alert.AlertType.INFORMATION, "Info", "Votre demande est en cours de traitement.");
                loadNotYetInterface(event);
            } else if (!BCrypt.checkpw(password, user.getPassword())) {
                // Log password comparison result for debugging
                System.out.println("Password comparison result: " + BCrypt.checkpw(password, user.getPassword()));
                showAlert(Alert.AlertType.ERROR, "Error", "Invalid password.");
            } else if ("admin".equals(user.getStatusCompte())) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Login successful as admin!");
                loadAdminDashboard(event);
            } else {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Login successful!");
                loadDashboard(event, user);
            }
        }
    }


    @FXML
    void inscriptionUser(ActionEvent event) {
        try {
            System.out.println(getClass().getResource("/inscription.fxml"));
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/inscription.fxml")));
            Stage stage = (Stage) inscriptionButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadDashboard(ActionEvent event, User user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Home.fxml"));
            Parent root = loader.load();

            HomeController homeController = loader.getController();
            homeController.initData(user.getId());

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void loadAdminDashboard(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../menuAdmin.fxml"));
            Parent root = loader.load();

            // Access the controller of the next scene and pass the user ID
            tn.esprit.fast_hk.fast_hk.controllers.MenuAdmin menuController = loader.getController();
            menuController.initData(1);

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadNotYetInterface(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../NotYet.fxml"));
            Parent root = loader.load();

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

    public void emailCode(MouseEvent mouseEvent) {
        String email = UserEmail.getText().trim();

        if (email.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Veuillez saisir votre adresse email d'abord.");
            return;
        }

        UserService userService = new UserService();
        User user = userService.findByEmail(email);

        if (user == null) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "L'adresse email n'existe pas dans la base de données.");
            return;
        }

        // Generate a random code
        String code = generateRandomCode();

        // Send email with code using Gmail SMTP
        sendEmail(email, code);

        // Prompt user to enter the verification code
        promptVerificationCode(code, email);
    }

    // Helper method to send email with random code using Gmail SMTP
    private void sendEmail(String toEmail, String code) {
        final String username = "kb6004710@gmail.com"; // Replace with your Gmail username
        final String password = "lieo phxq amee okkd"; // Replace with your Gmail password or app-specific password

        // Create a JSON payload for the API request
        JsonObject jsonPayload = new JsonObject();
        jsonPayload.addProperty("to", toEmail);
        jsonPayload.addProperty("subject", "Code de vérification");
        jsonPayload.addProperty("text", "Votre code de vérification est: " + code);

        // SMTP server properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Session object to authenticate with Gmail SMTP server
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a MimeMessage object
            Message message = new MimeMessage(session);

            // Set From: header field of the header
            message.setFrom(new InternetAddress(username));

            // Set To: header field of the header
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmail));

            // Set Subject: header field
            message.setSubject("Code de vérification");

            // Now set the actual message
            message.setText("Votre code de vérification est: " + code);

            // Send message
            Transport.send(message);

            // Show confirmation to the user
            showAlert(Alert.AlertType.INFORMATION, "Email Envoyé", "Un email avec le code de vérification a été envoyé.");

        } catch (MessagingException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors de l'envoi de l'email: " + e.getMessage());
            e.printStackTrace(); // Print stack trace for debugging
        }
    }

    private void promptNewPassword(String email) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Changer le mot de passe");

        // Set the button types
        ButtonType confirmButtonType = new ButtonType("Confirmer", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(confirmButtonType, ButtonType.CANCEL);

        // Create the password and confirmation fields
        PasswordField newPasswordField = new PasswordField();
        newPasswordField.setPromptText("Nouveau mot de passe");

        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Confirmer le mot de passe");

        GridPane grid = new GridPane();
        grid.add(new Label("Nouveau mot de passe:"), 0, 0);
        grid.add(newPasswordField, 1, 0);
        grid.add(new Label("Confirmer le mot de passe:"), 0, 1);
        grid.add(confirmPasswordField, 1, 1);

        dialog.getDialogPane().setContent(grid);

        // Enable/Disable confirm button depending on whether a password was entered
        Node confirmButton = dialog.getDialogPane().lookupButton(confirmButtonType);
        confirmButton.setDisable(true);

        // Add a listener to both password fields to enable the confirm button only when both fields have input
        newPasswordField.textProperty().addListener((observable, oldValue, newValue) ->
                confirmButton.setDisable(newValue.trim().isEmpty() || !newValue.equals(confirmPasswordField.getText()))
        );

        confirmPasswordField.textProperty().addListener((observable, oldValue, newValue) ->
                confirmButton.setDisable(newValue.trim().isEmpty() || !newValue.equals(newPasswordField.getText()))
        );

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == confirmButtonType) {
                return newPasswordField.getText();
            }
            return null;
        });

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(newPassword -> {
            if (newPassword.equals(confirmPasswordField.getText())) {
                updatePassword(email, newPassword);
            } else {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Les mots de passe ne correspondent pas.");
                promptNewPassword(email); // Retry if passwords do not match
            }
        });
    }

    private void promptVerificationCode(String expectedCode, String email) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Code de vérification");
        dialog.setHeaderText("Entrez le code de vérification reçu par email :");
        dialog.setContentText("Code :");

        // Show the dialog and wait for the user's response
        Optional<String> result = dialog.showAndWait();

        // Check if the user entered a code
        result.ifPresent(enteredCode -> {
            if (enteredCode.equals(expectedCode)) {
                showAlert(Alert.AlertType.INFORMATION, "Code Correct", "Le code est correct.");
                promptNewPassword(email); // Prompt for new password after correct code
            } else {
                showAlert(Alert.AlertType.ERROR, "Code Incorrect", "Le code entré est incorrect.");

                // Recursively prompt again for correct code
                promptVerificationCode(expectedCode, email);
            }
        });
    }

    // Existing method for updating the password
    private void updatePassword(String email, String newPassword) {
        UserService userService = new UserService();
        boolean isUpdated = userService.updatePassword(email, newPassword);

        if (isUpdated) {
            showAlert(Alert.AlertType.INFORMATION, "Succès", "Le mot de passe a été mis à jour avec succès.");
        } else {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors de la mise à jour du mot de passe.");
        }
    }

    // Helper method to generate a random code
    private String generateRandomCode() {
        Random random = new Random();
        int code = random.nextInt(9000) + 1000; // Generate a 4-digit random number
        return String.valueOf(code);
    }


}

