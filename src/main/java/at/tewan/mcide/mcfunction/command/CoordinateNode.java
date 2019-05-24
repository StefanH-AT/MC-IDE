package at.tewan.mcide.mcfunction.command;

import at.tewan.mcide.mcfunction.SyntaxPattern;

public class CoordinateNode extends CommandNode {
    protected CoordinateNode(SyntaxPattern syntax) {
        super(syntax);
    }

    @Override
    public String getCompletion() {
        return "~|^";
    }


}
