package tn.esprit.fastkh.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import org.controlsfx.control.Rating;
import tn.esprit.fastkh.models.Avis;
import tn.esprit.fastkh.models.User;
import tn.esprit.fastkh.services.AvisService;

public class AvisController {

    @FXML
    private Rating ratingField;

    @FXML
    private TextArea commentTextArea;

    @FXML
    private Label averageRatingLabel;

    private int bonsPlanId;
    private int userId;

    private final AvisService avisService = new AvisService();
    private User currentUser;

    public void setBonsPlanId(int bonsPlanId) {
        this.bonsPlanId = bonsPlanId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @FXML
    public void initialize() {
        updateAverageRating();
    }

    @FXML
    private void handleSubmitAvis() {
        int rating = (int) ratingField.getRating();
        String commentaire = commentTextArea.getText();

        // Debug prints to check values
        System.out.println("UserId: " + userId);
        System.out.println("BonsPlanId: " + bonsPlanId);

        Avis avis = new Avis(userId, bonsPlanId, rating, commentaire);
        avisService.add(avis);
        showAlert(Alert.AlertType.INFORMATION, "Avis Soumis", "Merci pour votre avis!");
        updateAverageRating();
    }


    private void updateAverageRating() {
        double average = avisService.getAverageRatingByBonsPlanId(bonsPlanId);
        averageRatingLabel.setText("Note Moyenne: " + String.format("%.1f", average));
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }


}
