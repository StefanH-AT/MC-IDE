package at.tewan.mcide.mcfunction.command.json;

import java.util.ArrayList;

public class CommandDefinitionNode {

    private String type;
    private String value;

    private ArrayList<CommandDefinitionNode> children;

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public ArrayList<CommandDefinitionNode> getChildren() {
        return children;
    }
}
