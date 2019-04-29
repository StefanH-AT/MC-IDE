package at.tewan.mcide.settings;

import at.tewan.mcide.settings.json.Settings;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GlobalSettings {

    private static String settingsFileName = "settings.definitions";
    private static File settingsFile = new File(settingsFileName);
    private static Settings settings;

    private static String defaultMcDir = "C:/Users/" + System.getProperty("user.name") + "/AppData/Roaming/.minecraft";

    private static Gson gson;

    public static void loadConfig() {
        gson = new Gson();

        if (settingsFile.exists()) {

            try {
                settings = gson.fromJson(new FileReader(settingsFile), Settings.class);
            } catch (IOException exception) {
                exception.printStackTrace();
            }

        } else {
            settings = new Settings();
            settings.setMcDir(defaultMcDir);

            saveConfig();
        }

        System.out.println("Minecraft directory: " + settings.getMcDir());
    }

    public static void saveConfig() {
        try {

            System.out.println("Generating default settings.definitions in " + settingsFile.toString());

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
