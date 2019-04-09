package at.tewan.mcide;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Resources {

    public void setProjectDestination(File rootDir) {

    }

    public static InputStream getLocalResource(String name) {
        return Resources.class.getClassLoader().getResourceAsStream(name);
    }

    public static Parent getFXML(String name) throws IOException {
        return new FXMLLoader().load(getLocalResource("javafx/" + name + ".fxml"));
    }

    // TODO Coden
    public static InputStream getProjectResource(String name) {
        return null;
    }
}