package at.tewan.mcide.app.controllers;

import at.tewan.mcide.project.Project;
import at.tewan.mcide.settings.GlobalSettings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerBuildConfigDialog implements Initializable {

    @FXML
    private Label worldname;

    FileChooser chooser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String worldNameDirectory = Project.getCurrentProjectConfig().getWorldname();

        if(worldNameDirectory == null) {
            worldname.setText("<none>");
        } else {
            worldname.setText(worldNameDirectory);
        }

        chooser = new FileChooser();
        chooser.setInitialDirectory(new File(GlobalSettings.getSettings().getMcDir()));
    }

    @FXML
    private void browseworld() {

    }

}
