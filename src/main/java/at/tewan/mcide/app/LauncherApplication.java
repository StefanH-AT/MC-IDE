package at.tewan.mcide.app;

import at.tewan.mcide.Resources;
import at.tewan.mcide.app.dialogs.NewProjectDialog;
import at.tewan.mcide.filters.ExtensionFilters;
import at.tewan.mcide.project.Project;
import at.tewan.mcide.project.json.ProjectConfig;
import at.tewan.mcide.settings.GlobalSettings;
import at.tewan.mcide.util.Themes;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class LauncherApplication extends Application {

    private Stage stage;


    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;

        GridPane root = new GridPane();
        Label recentProjectsLabel = new Label("Recent Projects");
        ListView<Project> recentProjects = new ListView<>();
        Button openProject = new Button("Open Project");
        Button newProject = new Button("New Project");

        openProject.setMaxWidth(Long.MAX_VALUE);
        openProject.setPrefHeight(50);
        openProject.setOnAction(openProjectHandler);

        newProject.setMaxWidth(Long.MAX_VALUE);
        newProject.setPrefHeight(50);
        newProject.setOnAction(newProjectHandler);

        recentProjects.setPrefSize(600, 400);
        recentProjects.setCellFactory(param -> {
            RecentProjectsCell cell = new RecentProjectsCell();
            return cell;
        });

        for(String projectConfigFilePath : GlobalSettings.getSettings().getRecentProjects()) {
            Project tempProject = new Project(new File(projectConfigFilePath));
            recentProjects.getItems().add(tempProject);
        }



        root.setPadding(new Insets(20));
        root.setVgap(5);
        root.getStylesheets().addAll(Themes.DEFAULT, Themes.getCurrentTheme());
        root.addRow(0, recentProjectsLabel);
        root.addRow(1, recentProjects);
        root.addRow(2, openProject);
        root.addRow(3, newProject);


        primaryStage.setTitle("MC-IDE Launcher");
        primaryStage.getIcons().add(new Image(Resources.getResource("img/icon.png"))); // TODO: Refactor Icon Zeug

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private EventHandler<ActionEvent> openProjectHandler = event -> {
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File(GlobalSettings.getSettings().getMcDir() + "/ide"));
        chooser.setTitle("Open Project");
        chooser.getExtensionFilters().add(ExtensionFilters.PROJECT_CFG);

        File output = chooser.showOpenDialog(stage);

        if(output != null) {
            try {
                // Das hier startet die MainApplication
                // Eine sehr kurze Zeile ;)
                MainApplication mainApplication = MainApplication.class.getConstructor(String.class).newInstance(output.toString());

                stage.hide();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private EventHandler<ActionEvent> newProjectHandler = event -> {
        NewProjectDialog newProjectDialog = new NewProjectDialog();
        newProjectDialog.show();
    };


    private class RecentProjectsCell extends ListCell<Project> {

        @Override
        protected void updateItem(Project item, boolean empty) {
            super.updateItem(item, empty);

            if(empty || item == null) return;

            GridPane root = new GridPane();
            Label projectTitleLabel = new Label(item.getProjectConfig().getName());
            projectTitleLabel.getStyleClass().add("recent-projects-title");
            Label projectAuthorLabel = new Label(item.getProjectConfig().getAuthor());

            root.add(projectTitleLabel, 0, 0);
            root.add(projectAuthorLabel, 0, 1);

            setGraphic(root);
        }
    }

}
