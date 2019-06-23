package at.tewan.mcide.app.subapps;

import at.tewan.mcide.Resources;
import at.tewan.mcide.app.subapp.BrowserApplication;
import at.tewan.mcide.app.subapp.SubApplication.SubApp;
import at.tewan.mcide.enums.PackType;
import at.tewan.mcide.mcfunction.Syntax;
import com.google.gson.Gson;
import javafx.scene.control.TabPane;

import java.io.InputStreamReader;

@SubApp
public class AppFunctions extends BrowserApplication {

    private TabPane codeTabs;
    private Syntax syntax;

    public AppFunctions() {
        super("Functions", PackType.DATAPACK, "functions");

        codeTabs = new TabPane();




        syntax = new Gson().fromJson(new InputStreamReader(Resources.getResource("syntax/mcfunction.json")), Syntax.class);
    }

    /*
    @Override
    public void openFile(File file) {

        // Für alle Tabs muss getestet werden, ob dieser bereits für die Datei zuständig ist,
        // die vom Nutzer geöffnet werden soll.
        for(Tab tab : codeTabs.getTabs()) {

            // Tab zu Code tab casten.
            // Typüberprüfung sollte eigentlich nicht notwendig sein, aber trotzdem hier nur für alle Fälle
            if(tab instanceof CodeTab) {
                CodeTab codeTab = (CodeTab) tab;

                // Wenn die Datei schon geöffnet ist, diesen Tab öffnen und methode hier abbrechen
                if(codeTab.getFile() == file) {
                    codeTabs.getSelectionModel().select(tab);
                    return;
                }
            }
        }

        // Wenn die Datei noch nicht geöffnet ist, wird ein neuer Tab erstellt
        codeTabs.getTabs().add(new CodeTab(codeTabs, file, syntax));
    }


     */
    @Override
    public void save() {
        System.out.println("Speichern");
    }

    @Override
    public void create() {

    }
}
