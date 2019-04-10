package at.tewan.mcide.app.controllers;

import at.tewan.mcide.filters.Filters;
import at.tewan.mcide.project.Project;
import at.tewan.mcide.versions.Version;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class ControllerNewProjectDialog implements Initializable {

    @FXML
    private TextField projectName, authorName, namespaceInput;

    @FXML
    private ListView<String> namespaceList;

    @FXML
    private ChoiceBox<String> mcVersion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        mcVersion.getItems().addAll(Version.getSupportedVersionsAsString(false));
        mcVersion.setValue(Version.getVersionAsName(Version.getNewestVersion()));


        namespaceInput.setTextFormatter(Filters.getNamespaceFilter());
        projectName.setTextFormatter(Filters.getNamespaceFilter());
    }

    @FXML
    private void addnamespace() {
        String input = namespaceInput.getText();
        ObservableList<String> list = namespaceList.getItems();

        if(!(list.contains(input) && list.isEmpty())) {
            list.add(input);
            namespaceInput.clear();
        }
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

    // TODO: Eingaben überprüfen
    @FXML
    private void createproject() {
        String name = projectName.getText();
        String author = authorName.getText();
        int version = Version.getVersionAsId(mcVersion.getValue());
        String[] namespaces = namespaceList.getItems().toArray(new String[namespaceList.getItems().size()]);

        if(namespaces.length == 0) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("At least one namespace must be specified.");
            alert.show();

        } else {
            Project.newProject(name, author, version, namespaces);
            projectName.getScene().getWindow().hide();
        }

    }
}
