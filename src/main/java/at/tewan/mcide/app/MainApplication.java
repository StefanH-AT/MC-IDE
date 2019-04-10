package at.tewan.mcide.app;

import at.tewan.mcide.Resources;
import at.tewan.mcide.project.Project;
import at.tewan.mcide.settings.GlobalSettings;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainApplication extends Application implements Initializable {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Minecraft IDE");

        Parent root = Resources.getFXML("main");
        Scene mainScene = new Scene(root);

        primaryStage.setScene(mainScene);

        Stage startDialog = new Stage();
        startDialog.setScene(new Scene(Resources.getFXML("startdialog")));
        startDialog.show();

        primaryStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
