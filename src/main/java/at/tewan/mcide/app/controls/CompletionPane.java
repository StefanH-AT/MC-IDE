package at.tewan.mcide.app.controls;

import at.tewan.mcide.mcfunction.command.*;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

/**
 * Die Completion Pane des mcfunction editors.
 * BEINHÄLT DEN KERN DES PARSERS
 *
 */
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
        completionListView.setOnMouseClicked(event -> insertCompletion());
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

        determineContext(area.getText(area.getCurrentParagraph()));

        if(completions.size() > 0) {
            completionListView.getSelectionModel().select(0);
            setVisible(true);
            completionListView.requestFocus();
        }
    }

    public void hide() {
        setVisible(false);
    }

    public void toggle() {
        if(isVisible()) hide();
        else show();
    }

    private void insertCompletion() {
        String textToInsert = completionListView.getSelectionModel().getSelectedItem();

        int pos = area.getCaretPosition();
        area.insertText(pos, textToInsert);
        area.requestFocus();

        hide();

    }

    private void determineContext(String line) {
        completions.clear();


        String[] lineArgs = line.split(" ");
        String cmdName = lineArgs[0];

        // Zeile leer, neuen Command anfangen
        if(line.trim().isEmpty()) {
            //completions.addAll(Commands.getCommands().keySet());
            addToCompletions(Commands.getCommands().values().toArray(new CommandNode[Commands.getCommands().size()]));
            return;
        }


        // execute as @e[type=pig run kill @s
        // CMD    CMD   SEL       REF CMD SEL

        // Das Argument das iteriert wird (Startet beim command namen)
        CommandNode currentArg = Commands.getCommands().get(cmdName);

        // =============================
        //      Autocomplete Logik
        // =============================
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

                // Reference nodes
                if(child instanceof ReferenceNode) {
                    ReferenceNode ref = (ReferenceNode) child;
                    break;
                }
            }

            // Wenn Argument letztes Element ist, alle Children in die Liste schreiben
            // Außerdem muss das
            if(i == lineArgs.length - 1) {
                addToCompletions(currentArg.getChildren());
                break;
            }
        }

    }

    private boolean isArgumentOfSameType(String argument, CommandNode expected) {
        return expected.checkPattern(argument);
    }

    private void addToCompletions(CommandNode[] nodes) {
        for(CommandNode node : nodes) {

            // Der Input String wird auf Pipes gesplittet
            // Beispiel: Selektoren werden durch Pipes terminiert, damit in einem String
            // alle übergeben werden können.
            // (@s @e @r...)
            completions.addAll(node.getCompletion().split("\\|"));
        }

        // Alphabetisch sortieren (Ergibt sinn oder?)
        completions.sort(String::compareTo);
    }
}
