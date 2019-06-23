package at.tewan.mcide.app.subapp;

import at.tewan.mcide.app.controls.BrowserTab;
import at.tewan.mcide.app.subapps.BrowserConfig;
import at.tewan.mcide.enums.PackType;
import at.tewan.mcide.project.Project;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.geometry.Orientation;
import javafx.geometry.Side;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.File;

import static at.tewan.mcide.enums.PackType.*;

public abstract class BrowserApplication extends SubApplication {

    private BrowserConfig config;

    private SplitPane splitPane;


    private TabPane namespaceTabPane;
    private BrowserTab[] namespaceTabs; // Ist ein Normales Array, weil die Anzahl der Namespaces sich nicht während der Laufzeit ändert.

    private TabPane fileTabPane;
    private ObservableMap<String, Tab> openFiles;

    public BrowserApplication(String displayName, PackType packType, String folder) {
        this(displayName, new BrowserConfig(packType, folder));
    }

    public BrowserApplication(String displayName, BrowserConfig config) {
        super(displayName);
        this.config = config;
        this.openFiles = FXCollections.observableHashMap();

        // SplitPane
        splitPane = new SplitPane();
        splitPane.setOrientation(Orientation.HORIZONTAL);
        splitPane.prefWidthProperty().bind(getRoot().widthProperty());
        splitPane.prefHeightProperty().bind(getRoot().heightProperty());
        splitPane.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if(event.isControlDown() && event.getCode() == KeyCode.S)
                save();
        });


        // Namespace Tab Pane
        namespaceTabPane = new TabPane();
        namespaceTabPane.setSide(Side.BOTTOM);
        namespaceTabPane.setMinWidth(200);
        namespaceTabPane.setMaxWidth(400);
        namespaceTabPane.prefHeightProperty().bind(splitPane.heightProperty());
        namespaceTabPane.getStyleClass().add("browser-namespaces");

        namespaceTabs = BrowserTab.getBrowserTabsForAllNamespaces(this);
        namespaceTabPane.getTabs().addAll(namespaceTabs);

        // File Tab Pane
        fileTabPane = new TabPane();
        fileTabPane.setSide(Side.TOP);
        fileTabPane.setMaxWidth(Double.MAX_VALUE);
        fileTabPane.prefHeightProperty().bind(splitPane.heightProperty());

        // Datei aus Map löschen, wenn ein Tab geschlossen wird
        fileTabPane.getTabs().addListener((ListChangeListener<Tab>) change -> {
            while(change.next())
                if(change.wasRemoved())
                    openFiles.values().removeAll(change.getRemoved());
        });

        // Tabs aktualisieren wenn die Map geändert wird.
        openFiles.addListener((MapChangeListener<String, Tab>) change -> {

            if(change.wasAdded()) {
                System.out.println("-> " +change.getKey());
                fileTabPane.getTabs().add(change.getValueAdded());
            } else {
                System.out.println("<- " +change.getKey());
                fileTabPane.getTabs().remove(change.getValueRemoved());
            }

        });

        // Elemente zur SplitPane hinzufügen
        splitPane.getItems().addAll(namespaceTabPane, fileTabPane);

        // SplitPane hinzufügen
        getRoot().getChildren().add(splitPane);

        getRoot().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if(event.getCode() == KeyCode.F5) {
                refresh();
            }
        });


        refresh();
    }

    private void refresh() {

        String rootDir = null;

        if(getConfig().getSearchedPack() == DATAPACK) {
            rootDir = Project.getDataDir();
        } else if(getConfig().getSearchedPack() == RESOURCEPACK) {
            rootDir = Project.getResourceDir();
        }

        for(BrowserTab tab : namespaceTabs) {
            tab.refresh(rootDir);
        }

    }

    //////////////////////////////////////////////
    //                                          //
    //             FILE MANAGEMENT              //
    //                                          //
    //////////////////////////////////////////////

    public void openFile(String file) {

        if(!openFiles.keySet().contains(file)) {
            Tab newTab = new Tab();
            newTab.setText(new File(file).getName());
            openFiles.put(file, newTab);

        } else {
            System.out.println(file + " is already opened");
        }
    }

    public abstract void save();

    //////////////////////////////////////////////
    //                                          //
    //             GETTER & SETTER              //
    //                                          //
    //////////////////////////////////////////////

    public BrowserTab[] getNamespaceTabs() {
        return namespaceTabs;
    }

    public BrowserConfig getConfig() {
        return config;
    }
}
