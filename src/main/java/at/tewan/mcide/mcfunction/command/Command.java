package at.tewan.mcide.mcfunction.command;

import at.tewan.mcide.mcfunction.SyntaxPattern;

public class Command extends CommandNode {

    private String name;

    public Command(SyntaxPattern syntaxPattern, String name, CommandNode... nodes) {
        super(syntaxPattern, nodes);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getCompletion() {
        return getName() + " ";
    }
}
