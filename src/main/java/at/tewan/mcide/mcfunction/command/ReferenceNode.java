package at.tewan.mcide.mcfunction.command;

public class ReferenceNode extends CommandNode {

    private Command reference;

    public Command getReference() {
        return reference;
    }

    ReferenceNode(String reference) {
        //this.reference = reference;
    }

    @Override
    public String getCompletion() {
        return null;
    }
}
