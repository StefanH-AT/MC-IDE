package at.tewan.mcide.settings;

import at.tewan.mcide.settings.json.Settings;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Enthält globale Einsellungen für MC-IDE.
 * Die config Datei wird im root Verzeichnis der Ausführung liegen.
 * */
public class GlobalSettings {

    private static String settingsFileName = "settings.json";
    private static File settingsFile = new File(settingsFileName);
    private static Settings settings;

    // TODO: Cross Platform-freundlich machen
    private static String defaultMcDir = System.getProperty("user.dir") + "AppData/Roaming/.minecraft";

    // Voll pretty Printing :3  // Es tut mir leid warum schreib ich das ohjemine oh nein oh bite nicht es funktioniert nichtmal
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void loadConfig() {


        if (settingsFile.exists()) {

            try {
                settings = gson.fromJson(new FileReader(settingsFile), Settings.class);
            } catch (IOException exception) {
                exception.printStackTrace();
            }

        } else {
            settings = new Settings();
            settings.setMcDir(defaultMcDir);
            settings.setTheme("dark");
            settings.setRecentProjects(new ArrayList<>());

            saveConfig();
        }

        System.out.println("Minecraft directory: " + settings.getMcDir());
    }

    /**
     * Speichert die Config als datei
     * */
    public static void saveConfig() {
        try {

            System.out.println("Generating default settings.json in " + settingsFile.toString());

            FileWriter writer = new FileWriter(settingsFile);
            writer.write(gson.toJson(settings));

            writer.close();

        } catch(IOException exception) {
            exception.printStackTrace();
        }
    }

    public static Settings getSettings() {
        return settings;
    }

}
