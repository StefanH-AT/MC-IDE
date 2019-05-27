package at.tewan.mcide.app.controllers;

import at.tewan.mcide.Resources;
import at.tewan.mcide.app.dialogs.NewProjectDialog;
import at.tewan.mcide.project.Project;
import at.tewan.mcide.settings.GlobalSettings;
import at.tewan.mcide.filters.ExtensionFilters;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class ControllerMain implements Initializable {

    @FXML
    private SubScene assetsScene, functionScene, recipesScene, advancementsScene, loottablesScene, tagsScene;

    @FXML
    private AnchorPane assetsTab, functionTab, recipesTab, advancementsTab, loottablesTab, tagsTab;

    @FXML
    private Label ramText;

    @FXML
    private ProgressBar ramProgress;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        bindProperties(assetsScene, assetsTab);
        bindProperties(functionScene, functionTab);
        bindProperties(recipesScene, recipesTab);
        bindProperties(advancementsScene, advancementsTab);
        bindProperties(loottablesScene, loottablesTab);
        bindProperties(tagsScene, tagsTab);

        try {

            assetsScene.setRoot(Resources.getFXML("assets"));
            functionScene.setRoot(Resources.getFXML("functions"));
            recipesScene.setRoot(Resources.getFXML("recipes"));
            advancementsScene.setRoot(Resources.getFXML("advancements"));
            loottablesScene.setRoot(Resources.getFXML("loottables"));
            tagsScene.setRoot(Resources.getFXML("tags"));

        } catch (IOException exception) {
            exception.printStackTrace();
        }

        // Ram Anzeige aktualisieren wenn sie angeklickt wird
        ramProgress.setOnMouseClicked(event -> updateRam());
        ramText.setOnMouseClicked(event -> updateRam());
        updateRam();
    }

    private void updateRam() {
        float max = Runtime.getRuntime().maxMemory() / 1048576;
        float used = Runtime.getRuntime().totalMemory() / 1048576;

        System.out.println(max + ", " + used + ", " + max / used / 100);

        ramProgress.setProgress(max / used / 100);
        ramText.setText((int) used + "/" + (int) max + "M");
    }

    private void bindProperties(SubScene scene, AnchorPane pane) {
        scene.widthProperty().bind(pane.widthProperty());
        scene.heightProperty().bind(pane.heightProperty());
    }

    @FXML
    private void newproject() {
        new NewProjectDialog().show();
    }

    @FXML
    private void openproject() {
        FileChooser ch = new FileChooser();
        ch.setTitle("Select Project");
        ch.getExtensionFilters().add(ExtensionFilters.PROJECT_CFG);
        ch.setInitialDirectory(new File(GlobalSettings.getSettings().getMcDir() + "/ide"));

        Project.load(ch.showOpenDialog(assetsScene.getScene().getWindow()).toString());
    }

    @FXML
    private void saveproject() {
        Project.save();
    }

    @FXML
    private void closeproject() {

    }

    @FXML
    private void projectsettings() {

    }

    @FXML
    private void settings() {

    }

    @FXML
    private void build() {
        Project.build(true, true);
    }

    @FXML
    private void buildresources() {
        Project.build(false, true);
    }

    @FXML
    private void builddata() {
        Project.build(true, false);
    }

    @FXML
    private void openbuildconfig() {

    }

    @FXML
    private void exit() {
        System.exit(-1);
    }
}
