package at.tewan.mcide.app.subapp;

import javafx.scene.Parent;

public interface SubApplicationContent {

    Parent getRoot();

    /**
     *
     * @return Gibt die Datei als String zurück, die dieser Controller darstellt.
     */
    String getContent();

    void onCreate();

}
