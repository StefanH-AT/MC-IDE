package at.tewan.mcide.mcfunction.command;

import at.tewan.mcide.mcfunction.SyntaxPattern;

public class NumberNode extends CommandNode {

    private float value;

    public NumberNode(SyntaxPattern syntaxPattern, float value) {
        super(syntaxPattern);
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    @Override
    public String getCompletion() {

        // Wenn es keine Nachkommerstellen gibt, diese abschneiden, damit 0 statt 0.0 ausgegeben wird
        if((int) value == value)
            return ((int) value) + " ";
        else
            return value + " ";
    }
}
