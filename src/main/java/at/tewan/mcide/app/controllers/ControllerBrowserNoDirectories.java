package at.tewan.mcide.app.controllers;

import at.tewan.mcide.project.Project;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerBrowserNoDirectories implements Initializable {

    static final boolean DATAPACK = true;
    static final boolean RESOURCEPACK = false;

    private String dir;

    @FXML
    private Accordion browser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refresh();
    }

    @FXML
    private void refresh() {
        for(String namespace : Project.getNamespaces()) {
            TitledPane pane = new TitledPane();
            pane.setText(namespace);
            browser.getPanes().add(pane);
        }
    }
}
