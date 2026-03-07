package seedu.address.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

public class InventoryListPanel extends UiPart<Region> {

    private static final String FXML = "InventoryListPanel.fxml";
    private static final int LOW_STOCK_THRESHOLD = 10;

    @FXML
    private ListView<String> inventoryListView;

    public InventoryListPanel() {
        super(FXML);

        ObservableList<String> fakeInventory = FXCollections.observableArrayList(
                "Product A:25",
                "Product B:18",
                "Product C:7",
                "Product D:3"
        );

        inventoryListView.setItems(fakeInventory);

        inventoryListView.setCellFactory(list -> new ListCell<>() {

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setGraphic(null);
                    setText(null);
                    return;
                }

                String[] parts = item.split(":");
                String name = parts[0];
                int qty = Integer.parseInt(parts[1]);

                // Product name
                Label nameLabel = new Label(name);
                nameLabel.setTextFill(Color.WHITE);
                nameLabel.setStyle("-fx-font-size: 14px;");

                // Quantity label
                Label qtyLabel = new Label(String.valueOf(qty));
                qtyLabel.setStyle("-fx-font-size: 14px;");

                if (qty > LOW_STOCK_THRESHOLD) {
                    qtyLabel.setStyle("-fx-text-fill: #00ff7f; -fx-font-size: 14px;");
                } else {
                    qtyLabel.setStyle("-fx-text-fill: #ff4c4c; -fx-font-size: 14px;");
                }

                // Spacer pushes quantity to the right
                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);

                HBox row = new HBox(nameLabel, spacer, qtyLabel);
                row.setSpacing(10);
                row.setPrefHeight(45);

                // Alternating row colors
                String backgroundColor;
                if (getIndex() % 2 == 0) {
                    backgroundColor = "#3a3f4b";
                } else {
                    backgroundColor = "#444a59";
                }

                row.setStyle(
                        "-fx-background-color: " + backgroundColor + ";" +
                        "-fx-padding: 12;"
                );

                // Remove default ListView styling interference
                setStyle("-fx-background-color: transparent;");
                setText(null);

                setGraphic(row);
            }
        });
    }
}
