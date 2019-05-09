package at.tewan.mcide.mcfiles.recipe;

import java.util.ArrayList;
import java.util.HashMap;

public class CraftingShaped extends Recipe {

    private ArrayList<String> pattern;
    private HashMap<String, CraftingKey> key;
    private CraftingResult result;

    public ArrayList<String> getPattern() {
        return pattern;
    }

    public void setPattern(ArrayList<String> pattern) {
        this.pattern = pattern;
    }

    public HashMap<String, CraftingKey> getKey() {
        return key;
    }

    public void setKey(HashMap<String, CraftingKey> key) {
        this.key = key;
    }

    public CraftingResult getResult() {
        return result;
    }

    public void setResult(CraftingResult result) {
        this.result = result;
    }
}
