package at.tewan.mcide.mcfunction.command;

public class SelectorNode extends CommandNode {

    private SelectorType type;

    public SelectorNode(SelectorType type, CommandNode... nodes) {
        super(nodes);
        this.type = type;
    }

    public enum SelectorType {
        a, r, p, e, s;

        public static String getAllSelectorTypesAsString() {
            String toReturn = "";

            for(SelectorType type : values()) {
                toReturn += "@" + type.name() + " ";
            }

            return toReturn;
        }
    }

    @Override
    public String getCompletion() {
        return SelectorType.getAllSelectorTypesAsString();
    }
}
