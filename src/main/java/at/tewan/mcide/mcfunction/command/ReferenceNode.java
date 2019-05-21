package at.tewan.mcide.mcfunction.command;

import at.tewan.mcide.mcfunction.SyntaxPattern;

public class ReferenceNode extends CommandNode {

    private Command reference;

    public Command getReference() {
        return reference;
    }

    ReferenceNode(SyntaxPattern syntaxPattern, String reference) {
        super(syntaxPattern);
        //this.reference = reference;
    }

    @Override
    public String getCompletion() {
        return null;
    }
}
