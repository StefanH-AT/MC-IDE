package at.tewan.mcide.app.controllers;

import at.tewan.mcide.project.Project;
import at.tewan.mcide.settings.GlobalSettings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerStartDialog implements Initializable {

    @FXML
    private Button button;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void openproject(ActionEvent e) {
        FileChooser projectChooser = new FileChooser();
        projectChooser.setInitialDirectory(new File(GlobalSettings.getSettings().getMcDir() + "/ide"));
        projectChooser.setTitle("Choose project");
        projectChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Project config","project.json"));
        File projectDir = projectChooser.showOpenDialog(button.getScene().getWindow());

        if(projectDir != null) {
            Project.load(projectDir.toString());
        } else {
            System.exit(-1);
        }
    }

    @FXML
    private void newproject(ActionEvent e) {

    }

}
