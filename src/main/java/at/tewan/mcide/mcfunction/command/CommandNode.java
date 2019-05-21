package at.tewan.mcide.mcfunction.command;

import at.tewan.mcide.mcfunction.SyntaxPattern;

import java.util.Arrays;

public abstract class CommandNode {

    private CommandNode[] children = null;

    private SyntaxPattern syntax;

    protected CommandNode(SyntaxPattern syntax, CommandNode... children) {
        this.children = children;
        this.syntax = syntax;

        System.out.println(syntax + getClass().getSimpleName());
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

    public boolean checkPattern(String arg) {
        return arg.matches(syntax.getPattern());
    }
}
