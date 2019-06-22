package at.tewan.mcide.app.subapp;

import at.tewan.mcide.app.controls.BrowserTab;
import at.tewan.mcide.app.subapps.BrowserConfig;
import at.tewan.mcide.enums.PackType;
import at.tewan.mcide.project.Project;
import javafx.geometry.Orientation;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.io.File;

import static at.tewan.mcide.enums.PackType.*;

public abstract class BrowserApplication extends SubApplication {

    private BrowserConfig config;



    private TabPane namespaceTabPane;

    private BrowserTab[] tabs;

    private SplitPane splitPane;
    private Parent area;

    public BrowserApplication(String displayName, PackType packType, String folder) {
        this(displayName, new BrowserConfig(packType, folder));
    }

    public BrowserApplication(String displayName, BrowserConfig config) {
        super(displayName);
        this.config = config;

        area = new AnchorPane();

        // SplitPane
        splitPane = new SplitPane();
        splitPane.setOrientation(Orientation.HORIZONTAL);
        splitPane.prefWidthProperty().bind(getRoot().widthProperty());
        splitPane.prefHeightProperty().bind(getRoot().heightProperty());

        namespaceTabPane = new TabPane();
        namespaceTabPane.setSide(Side.BOTTOM);
        namespaceTabPane.setMaxWidth(400);
        namespaceTabPane.prefHeightProperty().bind(splitPane.heightProperty());
        namespaceTabPane.getStyleClass().add("browser-namespaces");

        tabs = BrowserTab.getBrowserTabsForAllNamespaces(this);
        namespaceTabPane.getTabs().addAll(tabs);

        // Elemente zur SplitPane hinzufügen
        splitPane.getItems().addAll(namespaceTabPane, area);

        // SplitPane hinzufügen
        getRoot().getChildren().add(splitPane);

        getRoot().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if(event.getCode() == KeyCode.F5) {
                refresh();
            }
        });


        refresh();
    }

    protected void refresh() {

        String rootDir = null;

        if(getConfig().getSearchedPack() == DATAPACK) {
            rootDir = Project.getDataDir();
        } else if(getConfig().getSearchedPack() == RESOURCEPACK) {
            rootDir = Project.getResourceDir();
        }

        for(BrowserTab tab : tabs) {
            tab.refresh(rootDir);
        }

    }

    //////////////////////////////////////////////
    //                                          //
    //             ABSTRACT METHODS             //
    //                                          //
    //////////////////////////////////////////////

    public abstract void openFile(File file);

    //////////////////////////////////////////////
    //                                          //
    //             GETTER & SETTER              //
    //                                          //
    //////////////////////////////////////////////

    public BrowserTab[] getTabs() {
        return tabs;
    }

    @Override
    protected Parent getArea() {
        return area;
    }

    protected void setArea(Parent parent) {
        this.area = parent;
        splitPane.getItems().set(1, parent);
    }

    public BrowserConfig getConfig() {
        return config;
    }
}
