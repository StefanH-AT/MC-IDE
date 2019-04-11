package at.tewan.mcide.enums;

public enum Enchantment {

    // Universal
//    UNBREAKING(1000, null),
//    MENDING(1000, null),

    // Schwert und Bogen
    SHARPNESS(1000, Tool.SWORD, Tool.AXE),
    BANE_OF_ARTHROPODS(1000, Tool.SWORD),
    SMITE(1000, Tool.SWORD),
    FIRE_ASPECT(1000, Tool.SWORD),
    LOOTING(1000, Tool.SWORD, Tool.BOW, Tool.TRIDENT),
    POWER(1000, Tool.BOW),
    PUNCH(1000, Tool.BOW),
    FLAME(1000, Tool.BOW),

    // Spitzhacke Axt und Schaufel
    EFFICIENCY(1000, Tool.PICKAXE, Tool.AXE, Tool.SHOVEL),
    SILK_TOUCH(1000, Tool.PICKAXE, Tool.AXE, Tool.SHOVEL),
    FORTUNE(1000, Tool.SWORD, Tool.AXE, Tool.SHOVEL),

    // Rüstung
    PROTECTION(1000, Tool.HELMET, Tool.CHESTPLATE, Tool.LEGGINGS, Tool.BOOTS),
    FIRE_PROTECTION(1000, Tool.HELMET, Tool.CHESTPLATE, Tool.LEGGINGS, Tool.BOOTS),
    BLAST_PROTECTION(1000, Tool.HELMET, Tool.CHESTPLATE, Tool.LEGGINGS, Tool.BOOTS),
    PROJECTILE_PROTECTION(1000, Tool.HELMET, Tool.CHESTPLATE, Tool.LEGGINGS, Tool.BOOTS),
    FROST_WALKER(1000, Tool.BOOTS),
    DEPTH_STRIDER(1000, Tool.BOOTS),
    AQUA_AFFINITY(1000, Tool.HELMET);

    // TODO: Liste fertig machen

    private int version;
    private Tool[] items;

    private Enchantment(int version, Tool... items) {
        this.version = version;
        this.items = items;


    }

    private Enchantment(int version, boolean curse) {

    }

    public int getVersion() {
        return version;
    }

    public Tool[] getItems() {
        return items;
    }

}
