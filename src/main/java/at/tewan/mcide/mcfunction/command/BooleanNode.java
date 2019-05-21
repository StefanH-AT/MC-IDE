package at.tewan.mcide.mcfunction.command;

import at.tewan.mcide.mcfunction.SyntaxPattern;

public class BooleanNode extends CommandNode {

    private boolean value;

    public BooleanNode(SyntaxPattern syntaxPattern, boolean num) {
        super(syntaxPattern);
        this.value = num;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public String getCompletion() {
        return value + " ";
    }
}
