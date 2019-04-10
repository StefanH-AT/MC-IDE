package at.tewan.mcide.app;

import at.tewan.mcide.Resources;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApplication extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Minecraft IDE");

        Parent root = Resources.getFXML("main");
        Scene mainScene = new Scene(root);
        primaryStage.setScene(mainScene);

        primaryStage.show();
    }

}
