package at.tewan.mcide.app.controllers;

import javafx.event.ActionEvent;
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


    @FXML
    private void addNamespace(ActionEvent e) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        libRoot = new TreeItem<>("ProjectConfig");

        library.setRoot(libRoot);
    }
}
