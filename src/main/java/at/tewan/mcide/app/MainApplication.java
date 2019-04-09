package at.tewan.mcide.app;

import javafx.application.Application;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainApplication extends Application implements Initializable {

    public MainApplication(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Minecraft IDE");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
