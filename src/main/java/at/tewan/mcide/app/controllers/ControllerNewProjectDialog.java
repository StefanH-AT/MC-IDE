package at.tewan.mcide.app.controllers;

import at.tewan.mcide.filters.Filters;
import at.tewan.mcide.project.Project;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerNewProjectDialog implements Initializable {

    @FXML
    private TextField projectName, authorName;

    @FXML
    private ListView<String> namespaceList;

    @FXML
    private Button createProjectButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        projectName.setTextFormatter(Filters.getNamespaceFilter());

        namespaceList.getItems().addListener((ListChangeListener<String>) event -> {
            createProjectButton.setDisable(namespaceList.getItems().size() <= 0);
        });
    }

    @FXML
    private void addnamespace() {
        ObservableList<String> list = namespaceList.getItems();

        list.add("new_namespace");
    }

    @FXML
    private void removenamespace() {
        if(!namespaceList.getSelectionModel().isEmpty()) {
            String selected = namespaceList.getSelectionModel().getSelectedItem();
            ObservableList<String> list = namespaceList.getItems();

            if(list.contains(selected)) {
                list.remove(selected);
            }
        }
    }

    @FXML
    private void createproject() {
        String name = projectName.getText();
        String author = authorName.getText();
        String[] namespaces = namespaceList.getItems().toArray(new String[namespaceList.getItems().size()]);

        if(namespaces.length == 0) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("At least one namespace must be specified.");
            alert.show();

        } else {
            Project.newProject(name, author, namespaces);
            projectName.getScene().getWindow().hide();
        }

    }
}
