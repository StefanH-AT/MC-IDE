package at.tewan.mcide.mcfunction.command;

import at.tewan.mcide.mcfunction.SyntaxPattern;

public class NumberNode extends CommandNode {

    private float value;

    public NumberNode(SyntaxPattern syntaxPattern, float num) {
        super(syntaxPattern);
        this.value = num;
    }

    public float getValue() {
        return value;
    }

    @Override
    public String getCompletion() {
        return value + " ";
    }
}
