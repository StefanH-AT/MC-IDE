package at.tewan.mcide.app.controllers;

import at.tewan.mcide.app.NewRecipeDialog;
import at.tewan.mcide.app.factories.ItemSlotPane;
import at.tewan.mcide.app.factories.ListViewDragDrop;
import at.tewan.mcide.enums.RecipeType;
import at.tewan.mcide.item.Items;
import at.tewan.mcide.mcfiles.recipe.CraftingKey;
import at.tewan.mcide.mcfiles.recipe.CraftingShapeless;
import at.tewan.mcide.mcfiles.recipe.Recipe;
import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileReader;
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

        itemList.setDrop(false);

        ingredientListView.setItemLimit(9);
        ingredientListView.setRemoveOnDrag(true);

        dropDelete.setOnDragOver(event ->
            event.acceptTransferModes(TransferMode.ANY)
        );

    }

    @FXML
    private VBox dropDelete;

    @FXML
    private TextField itemSearch, resultCount, renameInput, groupInput;

    @FXML
    private ToggleButton regex;

    @FXML
    private ListViewDragDrop itemList;

    @FXML
    private GridPane craftingTable;

    @FXML
    private VBox ingredientList;

    @FXML
    private ListViewDragDrop ingredientListView;

    @FXML
    private ItemSlotPane result;

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
        Gson gson = new Gson();

        currentRecipe = gson.fromJson(new FileReader(file), Recipe.class);

        System.out.println(currentRecipe.getType());

        // Daten von Json in GUI laden.
        if(currentRecipe.getType().equals(RecipeType.CRAFTING_SHAPELESS.getName())) {
            CraftingShapeless shapeless = (CraftingShapeless) currentRecipe;
            ingredientListView.getItems().clear();

            for(CraftingKey key : shapeless.getIngredients()) {
                if(key.getItem() != null) ingredientListView.getItems().add(key.getItem());
                if(key.getTag() != null) ingredientListView.getItems().add(key.getTag());
            }
        }
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
        currentRecipe.setGroup(groupInput.getText());
        groupInput.clear();
    }

    @FXML
    private void rename() {

    }
}
