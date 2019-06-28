package at.tewan.mcide.app;

import at.tewan.mcide.Resources;
import at.tewan.mcide.app.dialogs.NewProjectDialog;
import at.tewan.mcide.filters.ExtensionFilters;
import at.tewan.mcide.project.Project;
import at.tewan.mcide.settings.GlobalSettings;
import at.tewan.mcide.util.ImageUtil;
import at.tewan.mcide.util.Themes;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class LauncherApplication extends Application {

    private Stage stage;

    private GridPane root;
    private Label recentProjectsLabel;
    private ListView<Project> recentProjects;
    private Button openProject;
    private Button newProject;
    private ImageView banner;
    private Label tipsTitleLabel;
    private HBox tipsPane;
    private Label tipsLabel;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;

        primaryStage.setResizable(false);

        root = new GridPane();
        recentProjectsLabel = new Label("Recent Projects");
        recentProjects = new ListView<>();
        openProject = new Button("Browse Project");
        newProject = new Button("New Project");
        banner = new ImageView();

        openProject.setMaxWidth(Long.MAX_VALUE);
        openProject.setPrefHeight(50);
        openProject.setOnAction(openProjectHandler);

        newProject.setMaxWidth(Long.MAX_VALUE);
        newProject.setPrefHeight(50);
        newProject.setOnAction(newProjectHandler);

        banner.setImage(ImageUtil.getImage("graphics/launcher_banner"));
        banner.setFitWidth(600);
        banner.setFitHeight(240);

        tipsTitleLabel = new Label("Important tip:");

        tipsPane = new HBox();
        tipsPane.getStyleClass().add("tips-box");
        tipsPane.setPadding(new Insets(10));

        tipsLabel = new Label("hat wer mein ds gesehn?");
        tipsPane.getChildren().add(tipsLabel);

        recentProjects.setPrefSize(600, 400);
        recentProjects.setOnMouseClicked(openRecentProjectHandler);
        recentProjects.setCellFactory(param -> {
            return new RecentProjectsCell();
        });

        for(String projectConfigFilePath : GlobalSettings.getSettings().getRecentProjects()) {
            Project tempProject = new Project(new File(projectConfigFilePath));
            recentProjects.getItems().add(tempProject);
        }



        root.setPadding(new Insets(20));
        root.setVgap(5);
        root.getStylesheets().addAll(Themes.DEFAULT, Themes.getCurrentTheme());
        root.addRow(0, banner);
        root.addRow(1, recentProjectsLabel);
        root.addRow(2, recentProjects);
        root.addRow(3, openProject);
        root.addRow(4, newProject);
        root.addRow(5, tipsTitleLabel);
        root.addRow(6, tipsPane);


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
            openProject(output);
        }
    };

    private EventHandler<MouseEvent> openRecentProjectHandler = event -> {
        if(event.getClickCount() == 2) {

            openProject(recentProjects.getSelectionModel().getSelectedItem().getProjectConfigFile());

        }
    };


    private void openProject(File projectConfigPath) {
        try {
            // Das hier startet die MainApplication
            MainApplication.class.getConstructor(File.class).newInstance(projectConfigPath);

            stage.hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

            Label projectAuthorLabel = new Label("Author: " + item.getProjectConfig().getAuthor());

            root.add(projectTitleLabel, 0, 0);
            root.add(projectAuthorLabel, 0, 1);

            setGraphic(root);
        }

    }

}
