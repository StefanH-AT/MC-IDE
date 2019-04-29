package at.tewan.mcide.app;

import at.tewan.mcide.Resources;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class Dialog {

    private Stage stage;
    private Scene scene;

    protected Dialog(String title, String fxmlName) {
        stage = new Stage();
        stage.setTitle(title);

        try {
            scene = new Scene(Resources.getFXML(fxmlName));
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        stage.setScene(scene);
    }

    public void show() {
        stage.show();
    }

    protected void hide() {
        stage.hide();
    }

    protected Stage getStage() {
        return stage;
    }

}
