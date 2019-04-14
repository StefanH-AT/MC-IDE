package at.tewan.mcide.app;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.Tab;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.fxmisc.richtext.CodeArea;

import java.io.*;
import java.nio.file.Files;

/**
 * Ein Tab für TabPane mit integrierter FeaturedCodeArea und Datei lade / speichersystem
 *
 *
 */
public class CodeTab extends Tab {

    private FeaturedCodeArea area;
    private File file;

    private boolean saved;

    public CodeTab(File initialFile) {
        area = new FeaturedCodeArea("mcfunction");
        file = initialFile;

        setContent(area);

        // initialFile ist null, wenn ein leerer Tab erstellt werden soll.
        if(initialFile == null) {
            setText("New file");
        } else {

            // Datei in die FeaturedCodeArea laden.
            if(initialFile.isFile()) {
                setText(initialFile.getName());

                try {
                    String content = "";
                    for(String a : Files.readAllLines(initialFile.toPath())) {
                        content += a + System.lineSeparator();
                    }
                    area.appendText(content);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        }

        // Wenn der Text verändert wird, soll die Datei als nicht gespeichert markiert werden.
        area.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->
            unsave()
        );

        // Event listener für Key combinations
        area.addEventFilter(KeyEvent.KEY_PRESSED, event -> {

            // Datei Speichern
            if(event.isControlDown() && event.getCode() == KeyCode.S) {
                save();
                event.consume();
            }

        });
    }

    /**
     * Datei wird als nicht gespeichert markiert.
     */
    private void unsave() {
        saved = false;
        setText("*" + getText());
    }

    private void save() {

        // Wenn die Datei bereits gespeichert ist, muss sie nicht erneut beschrieben werden.
        if(saved) return;

        try {
            FileWriter writer = new FileWriter(file) ;
            writer.write(area.getText());
            writer.close();

            setText(file.getName());

            System.out.println("Saved file " + file.toString());
            saved = true;
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    /**
     * Dieser Konstruktor erstellt einen neuen leeren Tab ohne Datei.
     */
    public CodeTab() {
        this(null);
    }

    public File getFile() {
        return file;
    }

    public FeaturedCodeArea getArea() {
        return area;
    }


}
