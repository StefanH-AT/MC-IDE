package at.tewan.mcide.app.controllers;

import at.tewan.mcide.project.Project;
import at.tewan.mcide.util.Icons;
import at.tewan.mcide.util.Util;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Abstrakte Klasse, die einen File browser implementiert.
 * Der Browser agiert entweder im Resource Pack oder im Data Pack (Wird im Konstruktor übergeben)
 *
 * Browser JavaFX Element ist ein TreeView mit fx:id "browser"
 * Methode refresh() ist auch als ActionEvent für Buttons ETC verfügbar.
 *
 */
public abstract class ControllerBrowser implements Initializable {

    static final boolean DATAPACK = true;
    static final boolean RESOURCEPACK = false;

    private String rootDir;

    private String rootName;
    private boolean packType;
    private List<String> directories;
    private List<String> directoryLabels;

    private Map<TreeItem, File> fileMap;

    private TreeItem<String> browserRoot;
    private String[] namespaces;

    ControllerBrowser(String rootName, boolean packType, String[] directories, String[] directoryLabels) {
        this.rootName = rootName;
        this.packType = packType;
        this.directories = Arrays.asList(directories);
        this.directoryLabels = Arrays.asList(directoryLabels);
    }

    @FXML
    protected TreeView browser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Click event handler
        browser.getSelectionModel().selectedItemProperty().addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
            if(observable.getValue() instanceof TreeItem) {
                TreeItem item = (TreeItem) observable.getValue();

                if(item.isLeaf() && item != browserRoot) {
                    try {
                        openFile(fileMap.get(item));
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        });

        refresh();
    }

    @FXML
    private void refresh() {

        if(packType == DATAPACK && Project.getProjectDir() != null) {
            rootDir = Project.getDataDir();
        } else {
            rootDir = Project.getResourceDir();
        }

        namespaces = Project.getNamespaces();
        browserRoot = getTreeRootWithNamespaces(rootName);
        fileMap = new HashMap<>();

        // Für jedes Namespace
        browserRoot.getChildren().forEach((namespace) -> {

            // Neue TreeItems für die directories erstellen
            TreeItem[] subdirs = new TreeItem[directories.size()];

            // Für dieses Subdirectory
            for(int i = 0; i < subdirs.length; i++) {

                // Subdirectory TreeItem befüllen
                subdirs[i] = new TreeItem<>(directoryLabels.get(i));
                subdirs[i].setExpanded(true);

                // Dateipfad crawlen
                File path = new File(
                        rootDir +
                        namespaces[browserRoot.getChildren().indexOf(namespace)] + "/" +
                        directories.get(i));

                for(File f : Util.getSubDirectories(path, new ArrayList<>())) {
                    String filePath = f.toString();
                    String cutPath = filePath.replace(path.toString(), "").substring(1);

                    TreeItem<String> leaf = new TreeItem<String>(cutPath, Icons.getIcon("mcfunction"));

                    fileMap.put(leaf, f);

                    subdirs[i].getChildren().add(leaf);
                }

            }

            namespace.getChildren().addAll(subdirs);

        });

        // Root setzen
        browser.setRoot(browserRoot);
    }

    protected abstract void openFile(File file) throws IOException;



    private static TreeItem<String>[] getNamespaceTreeItems() {
        String[] namespaces = Project.getNamespaces();

        TreeItem[] items = new TreeItem[namespaces.length];
        for(int i = 0; i < namespaces.length; i++) {
            items[i] = new TreeItem<>(namespaces[i]);
        }

        return items;
    }

    private static TreeItem<String> getTreeRootWithNamespaces(String rootName) {
        TreeItem root = new TreeItem(rootName);
        root.getChildren().addAll(getNamespaceTreeItems());

        root.setExpanded(true);
        return root;
    }
}
