package at.tewan.mcide.app.subapp;

import javafx.scene.Parent;

import java.io.File;

public interface SubApplicationContent {

    /**
     * Überschreibe diese Methode, um das untereste Element deines SubApplicationContents zurückzugeben.
     * @return Das unterste Element dieses SubApplicationContent's
     */
    Parent getRoot();

    /**
     *
     * @return Gibt die Datei als String zurück, die dieser Controller darstellt.
     */
    String onSave();

    /**
     * Wird bei Erstellung des SubApplicationContent's ausgeführt.
     * @param file Die Datei, die geöffnet wurde
     */
    void onCreate(File file);

    void postCreate();

}
