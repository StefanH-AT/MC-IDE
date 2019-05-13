package at.tewan.mcide.app.dialogs;

import at.tewan.mcide.Resources;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public abstract class Dialog {

    private Stage stage;
    private Scene scene;

    Dialog(String title, String fxmlName) {
        stage = new Stage();
        stage.setTitle(title);
        stage.initStyle(StageStyle.UTILITY);

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
