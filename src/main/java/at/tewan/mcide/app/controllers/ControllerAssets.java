package at.tewan.mcide.app.controllers;

import at.tewan.mcide.project.Project;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerAssets implements Initializable {

    @FXML
    private TreeView<String> library;
    private TreeItem<String> libRoot;
    private TreeItem<String>[] namespaceRoots;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        libRoot = new TreeItem<>("Namespaces");
        libRoot.setExpanded(true);
        library.setRoot(libRoot);

    }

    @FXML
    private void refresh() {

        String[] namespaces = Project.getNamespaces();
        namespaceRoots = new TreeItem[namespaces.length];
        for(int i = 0; i < namespaces.length; i++)
            namespaceRoots[i] = new TreeItem<>(namespaces[i]);

        libRoot.getChildren().clear();
        libRoot.getChildren().addAll(namespaceRoots);

    }
}
