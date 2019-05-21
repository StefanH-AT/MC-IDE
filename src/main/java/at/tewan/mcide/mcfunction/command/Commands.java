package at.tewan.mcide.mcfunction.command;

import at.tewan.mcide.Resources;
import at.tewan.mcide.mcfunction.Syntax;
import at.tewan.mcide.mcfunction.SyntaxPattern;
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

    public static void init(Syntax syntax) {
        Gson gson = new Gson();

        CommandDefinitions definitions = gson.fromJson(new InputStreamReader(Resources.getResource("definitions/commands.json")), CommandDefinitions.class);

        // Root Command Objekt erstellen
        for(CommandDefinitionNode node : definitions.getCommands()) {
            Command cmd = (Command) toCommandNode(syntax, node);
            cmd.setChildren(getChildren(syntax, node));

            COMMANDS.put(node.getValue(), cmd);

        }


    }

    // Rekursionen YAY!
    private static CommandNode[] getChildren(Syntax syntax, CommandDefinitionNode parent) {

        // Wenn das parent keine Kinder hat, null zurückgeben
        if(parent.getChildren() == null) return null;
        CommandNode[] children = new CommandNode[parent.getChildren().size()];

        // Alle Kinder zum parent hinzufügen
        for(int i = 0; i < children.length; i++) {

            children[i] = toCommandNode(syntax, parent.getChildren().get(i));
            children[i].setChildren(getChildren(syntax, parent.getChildren().get(i)));

        }

        return children;
    }

    // Commands Definition Datei wird hier geparst
    private static CommandNode toCommandNode(Syntax syntax, CommandDefinitionNode defNode) {
        CommandNode toReturn = null;

        String type = defNode.getType();
        switch (type) {
            case "command":
                toReturn = new Command(syntax.getSyntaxPattern(type), defNode.getValue());
            break;

            case "selector":
                toReturn = new SelectorNode(syntax.getSyntaxPattern(type), null);
            break;

            case "definitions":
                toReturn = new JsonNode(syntax.getSyntaxPattern(type), null);
            break;

            case "reference":
                toReturn = new ReferenceNode(syntax.getSyntaxPattern(type), defNode.getValue());
            break;

            case "content":
                toReturn = new ContentNode(syntax.getSyntaxPattern(type), defNode.getValue());
            break;

            case "number":
                toReturn = new NumberNode(syntax.getSyntaxPattern(type), 0);
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

    /**
     * @deprecated
     */
    public static CommandNode determineArgType(Syntax syntax, String arg) {
        /*
        System.out.println("Parsing '" + arg + "'");

        CommandNode toReturn;

        // Selector

        SyntaxPattern pattern = syntax.getName(arg);
        switch (pattern) {
            case "selector":
                toReturn = new SelectorNode(pattern, SelectorNode.SelectorType.valueOf(arg.charAt(1) + ""));
            break;

            case: "content":
                toReturn = new ContentNode(pattern, );
        }
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

        return toReturn; */
        return null;
    }
}
