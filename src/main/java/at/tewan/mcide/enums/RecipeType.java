package at.tewan.mcide.enums;

public enum RecipeType {

    CRAFTING_SHAPELESS("Crafting Shapeless"),
    CRAFTING_SHAPED("Crafting Shaped"),
    CRAFTING_SPECIAL("Crafting Special"),
    STONECUTTING("Stonecutting"),
    SMELTING("Smelting"),
    SMOKING("Smoking"),
    BLASTING("Blasting"),
    CAMPFIRE("Campfire");


    private String label;

    RecipeType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public String getName() {
        return this.name().toLowerCase();
    }

    public static String[] getAllLabels() {
        String[] arr = new String[values().length];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = values()[i].getLabel();
        }

        return arr;
    }
}
