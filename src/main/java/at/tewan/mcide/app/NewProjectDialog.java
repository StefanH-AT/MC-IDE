package at.tewan.mcide.app;

import at.tewan.mcide.Resources;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NewProjectDialog {

    private Stage stage;

    public NewProjectDialog() {
        stage = new Stage();
        stage.setTitle("Create new Project");

        try {
            stage.setScene(new Scene(Resources.getFXML("newproject")));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void show() {
        stage.show();
    }
}
