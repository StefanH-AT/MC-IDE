package at.tewan.mcide.enums;

public enum Enchantment {

    // Universal
    UNBREAKING(100, null),
    MENDING(100, null),

    // Schwert und Bogen
    SHARPNESS(100, Tool.SWORD, Tool.AXE),
    BANE_OF_ARTHROPODS(100, Tool.SWORD),
    SMITE(100, Tool.SWORD),
    FIRE_ASPECT(100, Tool.SWORD),
    LOOTING(100, Tool.SWORD, Tool.BOW, Tool.TRIDENT),
    POWER(100, Tool.BOW),
    PUNCH(100, Tool.BOW),
    FLAME(100, Tool.BOW),

    // Spitzhacke Axt und Schaufel
    EFFICIENCY(10, Tool.PICKAXE, Tool.AXE, Tool.SHOVEL),
    SILK_TOUCH(10, Tool.PICKAXE, Tool.AXE, Tool.SHOVEL),
    FORTUNE(10, Tool.SWORD, Tool.AXE, Tool.SHOVEL),

    // Rüstung
    PROTECTION(10, Tool.HELMET, Tool.CHESTPLATE, Tool.LEGGINGS, Tool.BOOTS),
    FIRE_PROTECTION(10, Tool.HELMET, Tool.CHESTPLATE, Tool.LEGGINGS, Tool.BOOTS),
    BLAST_PROTECTION(10, Tool.HELMET, Tool.CHESTPLATE, Tool.LEGGINGS, Tool.BOOTS),
    PROJECTILE_PROTECTION(10, Tool.HELMET, Tool.CHESTPLATE, Tool.LEGGINGS, Tool.BOOTS),
    FROST_WALKER(10, Tool.BOOTS),
    DEPTH_STRIDER(10, Tool.BOOTS),
    AQUA_AFFINITY(10, Tool.HELMET);

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
