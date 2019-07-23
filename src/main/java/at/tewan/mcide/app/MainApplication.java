package at.tewan.mcide.app;

import at.tewan.mcide.Resources;
import at.tewan.mcide.project.Project;
import at.tewan.mcide.util.ImageUtil;
import at.tewan.mcide.util.Themes;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.File;
import java.util.Optional;


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
        primaryStage.getIcons().add(ImageUtil.getImage("icon"));

        Parent root = Resources.getFXML("main");
        root.getStylesheets().add(Themes.getCurrentTheme());
        Scene mainScene = new Scene(root);
        primaryStage.setScene(mainScene);

        primaryStage.setOnCloseRequest(closeEvent);

        primaryStage.show();

    }

    private EventHandler<WindowEvent> closeEvent = event ->  {
        Dialog closeConfirmationDialog = new Dialog();
        DialogPane closeConfirmationDialogPane = closeConfirmationDialog.getDialogPane();

        closeConfirmationDialog.setTitle("Close Confirmation");
        closeConfirmationDialog.setContentText("Are you sure you want to exit?");

        closeConfirmationDialogPane.getStylesheets().addAll(Themes.getCurrentTheme(), Themes.DEFAULT);
        closeConfirmationDialogPane.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);

        Optional result = closeConfirmationDialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.NO) {
            event.consume();
        }

    };

}
