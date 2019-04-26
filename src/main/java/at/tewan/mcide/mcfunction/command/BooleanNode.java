package at.tewan.mcide.mcfunction.command;

public class BooleanNode extends CommandNode {

    private boolean value;

    public BooleanNode(boolean num) {
        this.value = num;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public String getCompletion() {
        return value + "";
    }
}
