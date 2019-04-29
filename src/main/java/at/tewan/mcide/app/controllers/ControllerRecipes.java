package at.tewan.mcide.app.controllers;

import at.tewan.mcide.app.NewRecipeDialog;
import at.tewan.mcide.item.Items;
import at.tewan.mcide.recipes.json.Recipe;
import com.sun.org.apache.bcel.internal.generic.DREM;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ControllerRecipes extends ControllerBrowserNoDirectories {

    private Recipe currentRecipe;

    private String[] filters = {"All Namespaces", "Only minecraft:", "Not minecraft:"};

    private final ObjectProperty<ListCell<String>> dragSource = new SimpleObjectProperty<>();

    public ControllerRecipes() {
        super("recipes", ControllerBrowserNoDirectories.DATAPACK);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        namespaceFilter.getItems().addAll(filters);
        namespaceFilter.setValue(filters[0]);

        updateitemlist();

        // Item List Dragging
        itemList.setCellFactory(lv -> {
            ListCell<String> cell = new ListCell<String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(item);
                }
            };

            cell.setOnDragDetected(event -> {
                Dragboard dragboard = cell.startDragAndDrop(TransferMode.COPY);
                ClipboardContent content = new ClipboardContent();

                content.putString(cell.getItem());
                dragboard.setContent(content);
                dragSource.set(cell);
            });

            return cell;
        });

        ingredientListView.setOnDragOver(event -> {
            Dragboard dragboard = event.getDragboard();
            if(dragboard.hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
        });

        // Shapeless List Dropping
        ingredientListView.setOnDragDropped(event -> {
            Dragboard dragboard = event.getDragboard();
            String text = (String) dragboard.getContent(DataFormat.PLAIN_TEXT);
            ingredientListView.getItems().add(text);
            ingredientListView.getItems().add(dragSource.get());
        });
    }

    @FXML
    private TextField itemSearch;

    @FXML
    private ToggleButton regex;

    @FXML
    private ListView<String> itemList;

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
    private ChoiceBox<String> namespaceFilter;

    @FXML
    private void newrecipe() {
        NewRecipeDialog dialog = new NewRecipeDialog(this);
        dialog.show();
    }

    @FXML
    private void deleteselected() {

    }

    @Override
    protected void openFile(File file) throws IOException {

    }

    @FXML
    private void updateitemlist() {
        String search = itemSearch.getText();
        String filter = namespaceFilter.getValue();

        itemList.getItems().clear();

        for(String item : Items.getAllItems()) {

            if(regex.isSelected()) {

                // Regex überprüfen für Item Suche
                if(item.matches(search) || search.isEmpty()) {

                    // TODO: Namespace Filter einbauen

                    itemList.getItems().add(item);
                }

            } else {

                if(item.contains(search)) {
                    itemList.getItems().add(item);
                }

            }


        }

    }
}
