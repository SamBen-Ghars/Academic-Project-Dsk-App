package tn.esprit.fastkh.utils;

import javafx.scene.control.ListCell;
import tn.esprit.fastkh.controllers.CardController;
import tn.esprit.fastkh.models.BonsPlans;

public class BonsPlansListCell extends ListCell<BonsPlans> {


    @Override
    protected void updateItem(BonsPlans item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            // Load CardLayout.fxml into the cell
            CardController cardController = new CardController();
            cardController.setData(item);
            setGraphic(cardController.getBox());
        }
    }
}
