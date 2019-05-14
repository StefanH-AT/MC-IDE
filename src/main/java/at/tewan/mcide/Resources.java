package at.tewan.mcide;

import at.tewan.mcide.settings.GlobalSettings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Resources {

    /**
     * @return InputStream der Resource die aus dem classpath geladen wird.
     *
     * */
    public static InputStream getResource(String name) {
        return Resources.class.getClassLoader().getResourceAsStream(name);
    }

    /**
     * @return Root Parent Node der FXML Datei
     * Lädt eine FXML Datei aus "/javafx/" aus dem Classpath und hängt ".fxml" an.
     * Beispiel: Wenn "main" übergeben wird, wird "/javafx/main.fxml" geladen.
     *
     * */
    public static Parent getFXML(String name) throws IOException {
        return FXMLLoader.load(Resources.class.getResource("/javafx/" + name + ".fxml"));
    }

    // TODO Coden
    public static InputStream getProjectResource(String name) {
        return null;
    }

    /**
     * @return Den Pfad zum workspace Directory (Standard .minecraft/ide)
     * */
    public static String getWorkspaceDir() {
        return GlobalSettings.getSettings().getMcDir() + "/ide/";
    }
}
