package at.tewan.mcide.app.controllers;

import at.tewan.mcide.app.subapp.SubApplication;
import at.tewan.mcide.app.dialogs.NewProjectDialog;
import at.tewan.mcide.project.Project;
import at.tewan.mcide.settings.GlobalSettings;
import at.tewan.mcide.filters.ExtensionFilters;
import at.tewan.mcide.util.Themes;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import org.reflections.Reflections;

public class ControllerMain implements Initializable {

    @FXML
    private TabPane subTabs;

    @FXML
    private Label ramText;

    @FXML
    private ProgressBar ramProgress;

    private SubApplication[] subApplications;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        registerSubApplications();

        // Ram Anzeige aktualisieren wenn sie angeklickt wird
        ramProgress.setOnMouseClicked(event -> updateRam());
        ramText.setOnMouseClicked(event -> updateRam());
        updateRam();
    }

    /**
     * Schulklassen, welche mit UnterAnwendung angemerkt wurden werden durch die
     * Spiegelung Bücherei erstellt. :)
     *
     * (Klassen die mit @SubApp annotated wurden, werden über Reflection erstellt)
     * */
    private void registerSubApplications() {

        Reflections reflections = new Reflections();
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(SubApplication.SubApp.class);

        subApplications = new SubApplication[annotated.size()];

        // Durch alle Subapps gehen
        for(Class<?> c : annotated) {

            // Neue Instanz der SubApp ersellen
            try {
                SubApplication obj = (SubApplication) c.newInstance();

                // SubApp zum Array hinzufügen
                for(int i = 0; i < subApplications.length; i++)
                    if(subApplications[i] == null)
                        subApplications[i] = obj;

                subTabs.getTabs().add(obj.getTab());

            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }



    }

    private void updateRam() {
        float max = Runtime.getRuntime().maxMemory() / 1048576;
        float used = Runtime.getRuntime().totalMemory() / 1048576;

        ramProgress.setProgress(max / used / 100);
        ramText.setText((int) used + "/" + (int) max + "M");
    }

    /**
     *
     * @return Die aktive SubApplication; null wenn keine offen ist (Was nicht möglich ist aber egal Java will dass ich irgendwas zurückgebe also mach ich das auch okay beschuldige nicht mich :'( )
     */
    private SubApplication getActiveSubApp() {
        //return null; // TODO: Den scheiß coden
        for(SubApplication subApplication : subApplications)
            if(subApplication.getTab().isSelected())
                return subApplication;
        return null;
    }
    /*
    @FXML
    private void newproject() {
        new NewProjectDialog().show();
    }

    @FXML
    private void openproject() {
        FileChooser ch = new FileChooser();
        ch.setTitle("Select Project");
        ch.getExtensionFilters().add(ExtensionFilters.PROJECT_CFG);
        ch.setInitialDirectory(new File(GlobalSettings.getSettings().getMcDir() + "/ide"));

        Project.load(ch.showOpenDialog(subTabs.getScene().getWindow()).toString());
    }
    */

    @FXML
    private void projectsettings() {

    }

    @FXML
    private void settings() {

    }

    @FXML
    private void build() {
        Project.build(true, true);
    }

    @FXML
    private void buildresources() {
        Project.build(false, true);
    }

    @FXML
    private void builddata() {
        Project.build(true, false);
    }

    @FXML
    private void openbuildconfig() {

    }

    @FXML
    private void exit() {
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.getDialogPane().getStylesheets().addAll(Themes.DEFAULT, Themes.getCurrentTheme());
        confirmAlert.setTitle("Are you sure you want to close this project?");
        confirmAlert.setContentText("All unsaved actions will be lost");

        ButtonType response = confirmAlert.showAndWait().get();

        if(response.getButtonData().isDefaultButton()) {
            Platform.exit();
        }
    }
}
