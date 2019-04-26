package at.tewan.mcide.mcfunction.command;

public class Command extends CommandNode {

    private String name;
    private CommandNode[] nodes;

    public Command(String name, CommandNode... nodes) {
        this.name = name;
        this.nodes = nodes;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getCompletion() {
        return getName();
    }
}
