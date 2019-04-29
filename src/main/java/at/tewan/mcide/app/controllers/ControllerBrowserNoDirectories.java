package at.tewan.mcide.app.controllers;

import at.tewan.mcide.project.Project;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public abstract class ControllerBrowserNoDirectories implements Initializable {

    ControllerBrowserNoDirectories(String rootName, boolean packType) {
        this.rootName = rootName;
        this.packType = packType;
    }

    private String rootName;
    private String rootDir;
    private boolean packType;

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

        if(packType == DATAPACK && Project.getProjectDir() != null) {
            rootDir = Project.getDataDir();
        } else {
            rootDir = Project.getResourceDir();
        }

        browser.getPanes().clear();

        for(String namespace : Project.getNamespaces()) {
            TitledPane pane = new TitledPane();
            ListView list = new ListView();

            File dir = new File(rootDir + "/" + namespace + "/" + rootName);
            System.out.println(dir.toString());
            for(File f : dir.listFiles()) {
                list.getItems().add(f.getName());
            }

            pane.setText(namespace);
            pane.setContent(list);


            browser.getPanes().add(pane);
        }
    }

    protected abstract void openFile(File file) throws IOException;
}
