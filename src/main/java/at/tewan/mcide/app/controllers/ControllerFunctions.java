package at.tewan.mcide.app.controllers;

import at.tewan.mcide.app.CodeTab;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerFunctions extends ControllerBrowser {

    @FXML
    private TabPane tabPane;


    public ControllerFunctions() {
        super("Functions", ControllerBrowser.DATAPACK, new String[]{"functions"}, new String[]{"Functions"});
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
    }

    // Superklasse führt diese Methode aus, wenn im File Browser eine Datei aufgerufen wird.
    // Für den mcfunction editor soll die Datei im Code Editor in einem Tab geöffnet werden.
    @Override
    protected void openFile(File file) throws IOException {

        // Für alle Tabs muss getestet werden, ob dieser bereits für die Datei zuständig ist,
        // die vom Nutzer geöffnet werden soll.
        for(Tab tab : tabPane.getTabs())  {

            // Tab zu Code tab casten.
            // Typüberprüfung sollte eigentlich nicht notwendig sein, aber trotzdem hier nur für alle Fälle
            if(tab instanceof  CodeTab) {
                CodeTab codeTab = (CodeTab) tab;

                // Wenn die Datei schon geöffnet ist, diesen Tab öffnen, ansonst neuen Tab erstellen
                if(codeTab.getFile() == file) {
                    tabPane.getSelectionModel().select(tab);
                } else {
                    tabPane.getTabs().add(new CodeTab(file));
                }
            }
        }
    }

    // Öffnet einen neuen leeren Tab
    @FXML
    private void newfile() {
        tabPane.getTabs().add(new CodeTab());
    }


}
