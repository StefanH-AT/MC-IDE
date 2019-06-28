package at.tewan.mcide.app;

import at.tewan.mcide.Resources;
import at.tewan.mcide.project.Project;
import at.tewan.mcide.util.Themes;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;


public class MainApplication extends Application {

    private File projectConfigFile;

    public MainApplication(File path) throws Exception {
        projectConfigFile = path;
        start(new Stage());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Project.load(projectConfigFile);

        primaryStage.setMaximized(true);
        primaryStage.setTitle("MC-IDE");
        primaryStage.getIcons().add(new Image(Resources.getResource("img/icon.png")));

        Parent root = Resources.getFXML("main");
        root.getStylesheets().add(Themes.getCurrentTheme());
        Scene mainScene = new Scene(root);
        primaryStage.setScene(mainScene);

        primaryStage.show();

    }

}
