package at.tewan.mcide.app.controllers;

import at.tewan.mcide.app.NewRecipeDialog;
import at.tewan.mcide.app.factories.ListCellDragable;
import at.tewan.mcide.item.Items;
import at.tewan.mcide.recipes.json.Recipe;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import javax.security.auth.callback.Callback;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ControllerRecipes extends ControllerBrowserNoDirectories {

    private Recipe currentRecipe;

    private String[] filters = {"All Namespaces", "Only minecraft:", "Not minecraft:"};

    public ControllerRecipes() {
        super("recipes", ControllerBrowserNoDirectories.DATAPACK);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        namespaceFilter.getItems().addAll(filters);
        namespaceFilter.setValue(filters[0]);

        updateitemlist();

        // TODO: Refactoren!!

        // Item List Dragging
        itemList.setCellFactory(view -> new ListCellDragable(false));

        ingredientListView.setCellFactory(lv -> {
            ListCell<String> cell = new ListCell<String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if(empty)
                        setText("");
                    else
                        setText(getIndex() + " " + item);
                }
            };

            cell.setOnDragDetected(event -> {
                Dragboard dragboard = cell.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();

                content.putString(cell.getText());
                dragboard.setContent(content);

                event.consume();
            });

            return cell;
        });



        // Shapeless List Dropping
        ingredientListView.setOnDragDropped(event -> {
            Dragboard dragboard = event.getDragboard();

            if(dragboard.hasString() && event.getGestureSource() != ingredientListView) {

                String text = dragboard.getString();

                if(ingredientListView.getItems().size() <= 9) {
                    ingredientListView.getItems().add(text);
                    ingredientListView.getItems().sort(String::compareTo);

                    event.setDropCompleted(true);
                }

                event.consume();
            }

        });



        dropDelete.setOnDragOver(event ->
            event.acceptTransferModes(TransferMode.ANY)
        );

        dropDelete.setOnDragDropped(event ->
            ingredientListView.getItems().remove(event.getDragboard().getString())
        );
    }

    @FXML
    private Pane dropDelete;

    @FXML
    private TextField itemSearch, resultCount, renameInput, groupInput;

    @FXML
    private ToggleButton regex;

    @FXML
    private ListView<String> itemList;

    @FXML
    private GridPane craftingTable;

    @FXML
    private VBox ingredientList;

    @FXML
    private ListView<String> ingredientListView;

    @FXML
    private Pane result;

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

    @FXML
    private void setgroup() {

    }

    @FXML
    private void rename() {

    }
}
