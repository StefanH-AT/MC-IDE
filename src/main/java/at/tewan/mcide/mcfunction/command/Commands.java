package at.tewan.mcide.mcfunction.command;

import at.tewan.mcide.Resources;
import at.tewan.mcide.mcfunction.command.json.CommandDefinitionNode;
import at.tewan.mcide.mcfunction.command.json.CommandDefinitions;
import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;

public class Commands {


    // Die Definition nodes werden direkt aus dem JSON heraus geladen und dann zu CommandNodes umgewandelt.
    // Command Nodes haben dann mehrere Funktionen für Syntax und completion.

    private static HashMap<String, Command> COMMANDS = new HashMap<>();

    public static void init() {
        Gson gson = new Gson();

        CommandDefinitions definitions = gson.fromJson(new InputStreamReader(Resources.getResource("definitions/commands_1132.json")), CommandDefinitions.class);

        // Root Command Objekt erstellen
        for(CommandDefinitionNode node : definitions.getCommands()) {
            Command cmd = (Command) toCommandNode(node);
            cmd.setChildren(getChildren(node));

            COMMANDS.put(node.getValue(), cmd);

        }


    }

    // Rekursionen YAY!
    private static CommandNode[] getChildren(CommandDefinitionNode parent) {

        // Wenn das parent keine Kinder hat, null zurückgeben
        if(parent.getChildren() == null) return null;
        CommandNode[] children = new CommandNode[parent.getChildren().size()];

        // Alle Kinder zum parent hinzufügen
        for(int i = 0; i < children.length; i++) {

            children[i] = toCommandNode(parent.getChildren().get(i));
            children[i].setChildren(getChildren(parent.getChildren().get(i)));

        }

        return children;
    }

    // Commands Definition Datei wird hier geparst
    private static CommandNode toCommandNode(CommandDefinitionNode defNode) {
        CommandNode toReturn = null;

        switch (defNode.getType()) {
            case "command":
                toReturn = new Command(defNode.getValue());
            break;

            case "subcommand":
                toReturn = new Command(defNode.getValue());
            break;

            case "selector":
                toReturn = new SelectorNode(null);
            break;

            case "definitions":
                toReturn = new JsonNode(null);
            break;

            case "reference":
                toReturn = new ReferenceNode(defNode.getValue());
            break;

            case "content":
                toReturn = new ContentNode(defNode.getValue());
            break;

            case "number":
                toReturn = new NumberNode(0);
            break;

            default:
                System.out.println("Command type " + defNode.getType() + " unknown. Returning NULL!");
        }

        return toReturn;
    }

    public static HashMap<String, Command> getCommands() {
        return COMMANDS;
    }

    public static Collection<String> getCommandsList() {
        return COMMANDS.keySet();
    }

    public static CommandNode determineArgType(String arg) {
        System.out.println("Parsing '" + arg + "'");

        CommandNode toReturn;

        // Selector
        if(arg.startsWith("@")) {
            toReturn = new SelectorNode(SelectorNode.SelectorType.valueOf(arg.charAt(1) + ""));
        } else if(arg.startsWith("{")) {
            toReturn = new JsonNode(arg);
        } else if(arg.matches("\\w+:\\w+")) {
            toReturn = new ContentNode(arg);
        } else if(arg.matches("[0-9.]+")) {
            toReturn = new NumberNode(Float.parseFloat(arg));
        } else if(arg.matches("\\w+")) {
            toReturn = new Command(arg);
        } else {
            toReturn = null;
        }

        System.out.println("Interpreted '" + arg + "' as " + toReturn.getClass().getSimpleName());

        return toReturn;
    }
}
