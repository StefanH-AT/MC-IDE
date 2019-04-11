package at.tewan.mcide;

import at.tewan.mcide.settings.GlobalSettings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Resources {

    public static InputStream getLocalResource(String name) {
        return Resources.class.getClassLoader().getResourceAsStream(name);
    }

    public static Parent getFXML(String name) throws IOException {
        return FXMLLoader.load(Resources.class.getResource("/javafx/" + name + ".fxml"));
    }

    // TODO Coden
    public static InputStream getProjectResource(String name) {
        return null;
    }

    public static String getWorkspaceDir() {
        return GlobalSettings.getSettings().getMcDir() + "/ide/";
    }
}
