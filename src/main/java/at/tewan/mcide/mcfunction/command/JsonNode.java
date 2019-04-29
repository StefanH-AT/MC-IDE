package at.tewan.mcide.mcfunction.command;

public class JsonNode extends CommandNode {

    private String json;

    public JsonNode(String jsonText, CommandNode... nodes) {
        super(nodes);
    }

    @Override
    public String getCompletion() {
        return "{definitions}";
    }
}
