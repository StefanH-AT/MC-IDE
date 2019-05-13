package at.tewan.mcide.app.dialogs;

import at.tewan.mcide.app.controllers.ControllerRecipes;

public class NewRecipeDialog extends Dialog {

    private ControllerRecipes controller;
    public static NewRecipeDialog dialog;

    public NewRecipeDialog(ControllerRecipes controller) {
        super("New Recipe", "newrecipe");
        this.controller = controller;
        dialog = this;
    }

    public void create(String name, String group, String type) {
        this.hide();
    }
}
