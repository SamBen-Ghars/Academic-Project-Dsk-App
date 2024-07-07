package tn.esprit.fastkh.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import tn.esprit.fastkh.controllers.CardController;
import tn.esprit.fastkh.models.BonsPlans;
import tn.esprit.fastkh.models.User;

import java.io.IOException;

public class BonsPlansListCell extends ListCell<BonsPlans> {

    private User currentUser;

    public BonsPlansListCell(User currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    protected void updateItem(BonsPlans item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/CardLayout.fxml"));
                HBox box = loader.load();

                CardController cardController = loader.getController();
                cardController.setData(item, currentUser);
                setGraphic(box);
            } catch (IOException e) {
                e.printStackTrace();
                setText("Error loading card layout");
                setGraphic(null);
            }
        }
    }
}
