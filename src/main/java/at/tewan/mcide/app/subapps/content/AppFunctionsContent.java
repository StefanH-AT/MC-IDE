package at.tewan.mcide.app.subapps.content;

import at.tewan.mcide.Resources;
import at.tewan.mcide.app.controls.CompletionPane;
import at.tewan.mcide.app.controls.FeaturedCodeArea;
import at.tewan.mcide.app.subapp.SubApplicationContent;
import at.tewan.mcide.mcfunction.Syntax;
import com.google.gson.Gson;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;

public class AppFunctionsContent implements SubApplicationContent {

    private StackPane stackPane;
    private CompletionPane completionPane;
    private FeaturedCodeArea codeArea;

    @Override
    public void onCreate(File file) {

        // ============
        // GUI Bauen
        // ============
        stackPane = new StackPane();
        codeArea = new FeaturedCodeArea(new Gson().fromJson(new InputStreamReader(Resources.getResource("syntax/mcfunction.json")), Syntax.class)); // TODO: WARUM IST DAS IN EINER FUCKING ZEILE CMON
        completionPane = new CompletionPane(codeArea);

        stackPane.setAlignment(Pos.TOP_LEFT);

        stackPane.getChildren().addAll(codeArea, completionPane);

        // ================
        // Datei laden
        // ================

        try {
            String content = "";
            for(String a : Files.readAllLines(file.toPath())) {
                content = content.concat(a + System.lineSeparator());
            }
            codeArea.appendText(content);
        } catch (IOException exception) {
            exception.printStackTrace();
        }


    }

    @Override
    public Parent getRoot() {
        return stackPane;
    }

    @Override
    public String onSave() {
        return null;
    }

    @Override
    public void postCreate() {

    }
}