package at.tewan.mcide.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class ControllerRecipes extends ControllerBrowserNoDirectories {

    @FXML
    private TextField itemSearch;

    @FXML
    private ListView itemList;

    @FXML
    private GridPane craftingTable;

    @FXML
    private VBox ingredientList;

    @FXML
    private ListView ingredientListView;

    @FXML
    private Pane result;

    @FXML
    private TextField resultCount;

    @FXML
    private StackPane stackPane;

    @FXML
    private void newrecipe() {

    }

    @FXML
    private void deleteselected() {

    }

}
