package at.tewan.mcide.mcfunction.command;

import at.tewan.mcide.mcfunction.SyntaxPattern;

public abstract class CommandNode {

    private CommandNode[] children;

    private SyntaxPattern syntax;

    protected CommandNode(SyntaxPattern syntax, CommandNode... children) {
        this.children = children;
        this.syntax = syntax;
    }

    public void setChildren(CommandNode[] children) {
        this.children = children;
    }

    public CommandNode[] getChildren() {
        if(children == null) return null;
        else return children;
    }

    public abstract String getCompletion();

    public boolean checkPattern(String arg) {
        return arg.matches(syntax.getPattern());
    }
}
