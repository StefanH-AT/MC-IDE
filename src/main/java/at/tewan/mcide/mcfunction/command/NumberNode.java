package at.tewan.mcide.mcfunction.command;

public class NumberNode extends CommandNode {

    private float value;

    public NumberNode(float num) {
        this.value = num;
    }

    public float getValue() {
        return value;
    }

    @Override
    public String getCompletion() {
        return value + "";
    }
}
