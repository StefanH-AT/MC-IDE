package at.tewan.mcide.mcfunction.command;

import at.tewan.mcide.mcfunction.SyntaxPattern;

public class JsonNode extends CommandNode {

    private String json;

    public JsonNode(SyntaxPattern syntaxPattern, String jsonText, CommandNode... nodes) {
        super(syntaxPattern, nodes);
    }

    @Override
    public String getCompletion() {
        return "{definitions}";
    }
}
