package at.tewan.mcide.mcfunction.command;

import java.util.Arrays;

public abstract class CommandNode {

    private CommandNode[] children = null;

    CommandNode(CommandNode... children) {
        this.children = children;
    }

    public void setChildren(CommandNode[] children) {
        this.children = children;
    }

    public CommandNode[] getChildren() {
        if(children == null) return null;
        else return children;
    }

    public CommandNode addChild(CommandNode child) {
        children = Arrays.copyOf(children, children.length + 1);
        children[children.length] = child;

        return this;
    }

    public abstract String getCompletion();
}
