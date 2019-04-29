package at.tewan.mcide.app.controllers;

import at.tewan.mcide.app.NewRecipeDialog;
import at.tewan.mcide.filters.Filters;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerNewRecipeDialog implements Initializable {

    @FXML
    private TextField name, group;

    @FXML
    private ChoiceBox<String> type;

    private String[] availibleRecipeTypes = {
            "Shaped Crafting",
            "Shapeless Crafting",
            "Stonecutting",
            "Smelting",
            "Smoking",
            "Blasting",
            "Campfire",
            "Crafting Special"
    };

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        type.getItems().addAll(availibleRecipeTypes);
        type.setValue(availibleRecipeTypes[0]);

        name.setTextFormatter(Filters.getNamespaceFilter());
        group.setTextFormatter(Filters.getNamespaceFilter());
    }

    @FXML
    private void create() {

        if(!name.getText().isEmpty()) {
            NewRecipeDialog.dialog.create(name.getText(), group.getText(), type.getValue());
        } else {
            name.setStyle("-fx-border-color: red");
        }

    }
}
