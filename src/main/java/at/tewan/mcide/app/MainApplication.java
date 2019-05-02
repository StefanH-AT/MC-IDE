package at.tewan.mcide.app;

import at.tewan.mcide.Resources;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class MainApplication extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setMaximized(true);
        primaryStage.setTitle("MC-IDE");
        primaryStage.initStyle(StageStyle.DECORATED);

        Parent root = Resources.getFXML("main");
        Scene mainScene = new Scene(root);
        primaryStage.setScene(mainScene);

        primaryStage.show();

    }

}
