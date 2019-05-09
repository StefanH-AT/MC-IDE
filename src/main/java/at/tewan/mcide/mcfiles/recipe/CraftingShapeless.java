package at.tewan.mcide.mcfiles.recipe;

import java.util.ArrayList;

public class CraftingShapeless extends Recipe {

    private ArrayList<CraftingKey> ingredients;
    private CraftingResult result;

    public ArrayList<CraftingKey> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<CraftingKey> ingredients) {
        this.ingredients = ingredients;
    }

    public CraftingResult getResult() {
        return result;
    }

    public void setResult(CraftingResult result) {
        this.result = result;
    }
}
