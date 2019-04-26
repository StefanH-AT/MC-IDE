package at.tewan.mcide.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ControllerRecipes extends ControllerBrowserNoDirectories {

    ControllerRecipes() {
        super("recipes", ControllerBrowserNoDirectories.DATAPACK);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
    }

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

    @Override
    protected void openFile(File file) throws IOException {

    }
}
