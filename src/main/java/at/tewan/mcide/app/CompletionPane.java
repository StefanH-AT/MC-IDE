package at.tewan.mcide.app;

import at.tewan.mcide.mcfunction.command.Command;
import at.tewan.mcide.mcfunction.command.CommandNode;
import at.tewan.mcide.mcfunction.command.Commands;
import at.tewan.mcide.mcfunction.command.SelectorNode;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

public class CompletionPane extends VBox {

    private FeaturedCodeArea area;
    private ListView<String> completionListView;
    private ObservableList<String> completions;

    public CompletionPane(FeaturedCodeArea area) {
        this.area = area;

        // Standardwerte setzen
        setMinWidth(200);
        setMaxWidth(200);

        setMinHeight(80);
        setMaxHeight(160);

        setId("completion-pane");
        getStylesheets().add("javafx/theme.css");
        hide();

        // Inhalt bauen
        completionListView = new ListView<>();
        completionListView.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER)
                insertCompletion();
        });
        completionListView.setOnMouseClicked(event -> {
            insertCompletion();
        });
        completions = completionListView.getItems();
        getChildren().add(completionListView);

        // Caret folgen
        area.caretBoundsProperty().addListener((ob, oVal, nVal) -> nVal.ifPresent(screen -> {
            Bounds local = area.screenToLocal(screen);

            int offsetX = -10;
            int offsetY = 0;

            setTranslateX(local.getMinX() + offsetX);
            setTranslateY(local.getMaxY() + offsetY);

        }));

        area.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if(event.isControlDown() && event.getCode() == KeyCode.SPACE) {
                toggle();
            }

            if(event.getCode() == KeyCode.ESCAPE) {
                hide();
            }
        });

        addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if(event.getCode() == KeyCode.ESCAPE) {
                hide();
                area.requestFocus();
            }
        });
    }

    public void show() {
        setVisible(true);
        determineContext(area.getText(area.getCurrentParagraph()));
        completionListView.requestFocus();
        if(completions.size() > 0)
            completionListView.getSelectionModel().select(0);
    }

    public void hide() {
        setVisible(false);
    }

    public void toggle() {
        if(isVisible()) hide();
        else show();
    }

    private void insertCompletion() {
        String textToInsert = completionListView.getSelectionModel().getSelectedItem() + " ";

        int pos = area.getCaretPosition();
        area.insertText(pos, textToInsert);
        area.requestFocus();

        hide();

    }

    private void determineContext(String line) {
        completions.clear();
        System.out.println("Analysiere '" + line + "' ...");


        String[] lineArgs = line.split(" ");
        String cmdName = lineArgs[0];

        System.out.println("Command Name: " + cmdName);

        // Zeile leer, neuen Command anfangen
        if(line.trim().isEmpty()) {
            completions.addAll(Commands.getCommands().keySet());
            return;
        }

        // Wenn der Command falsch geschrieben wurde, abbrechen
        if(!Commands.getCommandsList().contains(cmdName)) return;

        // execute as @e[type=pig run kill @s
        // CMD    CMD   SEL       REF CMD SEL

        // TODO: Fixen

        // Das Argument das iteriert wird (Startet beim command namen)
        CommandNode currentArg = Commands.getCommands().get(cmdName);

        // Alle Argumente iterieren
        for(int i = 0; i < lineArgs.length; i++) {
            String arg = lineArgs[i];

            for(CommandNode child : currentArg.getChildren()) {

                // Überprüfen, ob der Typ übereinstimmt
                if(isArgumentOfSameType(arg, child)) {

                    // Bei Commands muss dazu noch der Name getestet werden
                    if(child instanceof Command) {
                        // Wenn der Name nicht übereinstimmt, überspringen
                        if(!((Command) child).getName().equals(arg)) continue;
                    }

                    currentArg = child;
                    break;
                }
            }

            // Wenn Argument letztes Element ist, alle Children in die Liste schreiben
            if(i == lineArgs.length - 1) {
                addToCompletions(currentArg.getChildren());
                break;
            }
        }

        // Das Argument, das gerade analysiert wird
        /*CommandNode currentArg = Commands.getCommands().get(cmdName);
        for(int i = 0; i < lineArgs.length; i++) {
            String arg = lineArgs[i];
            CommandNode[] currentArgChildren = currentArg.getChildren();

            CommandNode argAsNode = Commands.determineArgType(arg);
            System.out.println("Current arg: " + arg + " (" + currentArg.getClass().getSimpleName() + ")");

            // Linked list durchsuchen
            for(CommandNode child : currentArgChildren) {

                // Wenn die linked list ein child mit gleichem command Attribut typ hat
                if(argAsNode.getClass() == child.getClass()) {

                    if(child instanceof Command) {
                        String childName = ((Command) child).getName();
                        System.out.println(childName);
                        if(!childName.equals(arg)) continue;
                    }

                    // Wenn es das letzte Attribut ist, completion zur Liste hinzufügen
                    // Ansonsten Atribut setzen
                    if(i == lineArgs.length - 1) {

                        // Command
                        if(child instanceof Command) {
                            completions.add(((Command) child).getName());
                        }

                        if(child instanceof SelectorNode) {
                            for(SelectorNode.SelectorType val : SelectorNode.SelectorType.values()) {
                                completions.add("@" + val.name());
                            }
                        }

                    } else {
                        currentArg = child;
                        continue;
                    }

                }
            }
        }

        */


    }

    private boolean isArgumentOfSameType(String argument, CommandNode expected) {

        if(expected instanceof Command) {
            return argument.equals(((Command) expected).getName());
        } else if(expected instanceof SelectorNode) {
            return SelectorNode.SelectorType.getAllSelectorTypesAsString().contains(argument);
        } else {
            return false;
        }
    }

    private void addToCompletions(CommandNode... nodes) {
        for(CommandNode node : nodes) {

            // Der Input String wird auf Leerzeichen gesplittet
            // Beispiel: Selektoren werden durch Leerzeichen terminiert, damit in einem String
            // alle übergeben werden können.
            // (@s @e @r...)
            completions.addAll(node.getCompletion().split(" "));
        }

        // Alphabetisch sortieren (Ergibt sinn oder?)
        completions.sort(String::compareTo);
    }
}
