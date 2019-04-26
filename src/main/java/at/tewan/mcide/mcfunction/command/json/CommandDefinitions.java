package at.tewan.mcide.mcfunction.command.json;

import java.util.ArrayList;

public class CommandDefinitions {
    private int version;
    private ArrayList<CommandDefinitionNode> commands;

    public int getVersion() {
        return version;
    }

    public ArrayList<CommandDefinitionNode> getCommands() {
        return commands;
    }
}
