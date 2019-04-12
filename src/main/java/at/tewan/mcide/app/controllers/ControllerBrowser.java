package at.tewan.mcide.app.controllers;

import at.tewan.mcide.project.Project;
import at.tewan.mcide.util.Icons;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import sun.reflect.generics.tree.Tree;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public abstract class ControllerBrowser implements Initializable {

    static final boolean DATAPACK = true;
    static final boolean RESOURCEPACK = false;

    private String rootDir;

    private String rootName;
    private boolean packType;
    private String[] directories;
    private String[] directoryLabels;

    ControllerBrowser(String rootName, boolean packType, String[] directories, String[] directoryLabels) {
        this.rootName = rootName;
        this.packType = packType;
        this.directories = directories;
        this.directoryLabels = directoryLabels;
    }

    @FXML
    protected TreeView browser;

    private TreeItem<String> browserRoot;

    private String[] namespaces;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

        // Für jedes Namespace
        browserRoot.getChildren().forEach((namespace) -> {

            // Neue TreeItems für die directories erstellen
            TreeItem[] subdirs = new TreeItem[directories.length];

            // Für deses Subdirectory
            for(int i = 0; i < subdirs.length; i++) {

                // Subdirectory TreeItem befüllen
                subdirs[i] = new TreeItem<>(directoryLabels[i]);
                subdirs[i].setExpanded(true);

                // Dateipfad crawlen
                File path = new File(
                        rootDir +
                        namespaces[browserRoot.getChildren().indexOf(namespace)] + "/" +
                        directories[i]);

                for(File f : getSubDirectories(path, new ArrayList<>())) {
                    String filePath = f.toString();
                    String cutPath = filePath.replace(path.toString(), "").substring(1);

                    TreeItem<String> fileItem = new TreeItem<String>(cutPath, Icons.getIcon("mcfunction"));

                    /*
                        fileItem.addEventHandler(, event -> {
                            System.out.println(f.toString());
                            try {
                                openFile(f);
                            } catch(IOException exception) {
                                exception.printStackTrace();
                            }
                        });
                    */



                    subdirs[i].getChildren().add(fileItem);
                }

            }

            namespace.getChildren().addAll(subdirs);

        });

        // Click event handler
        browser.getSelectionModel().selectedItemProperty().addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
            if(observable.getValue() instanceof TreeItem) {
                TreeItem item = (TreeItem) observable.getValue();

                if(item.isLeaf()) {
                    System.out.println(directoryLabelToDirectory((String) item.getParent().getValue()));
                }
            }
        });

        // Root setzen
        browser.setRoot(browserRoot);
    }

    protected abstract void openFile(File file) throws IOException;

    /**
     * @param rootDir Ordner bei dem die Suche gestartet werden soll
     * @param list ArrayList die befüllt werden soll
     * @return ArrayList von Subdirectories
     */
    private ArrayList<File> getSubDirectories(File rootDir, ArrayList<File> list) {

        for(File f : rootDir.listFiles()) {
            if(f.isDirectory()) {
                getSubDirectories(f, list);
            } else if(f.isFile()) {
                list.add(f);
            }
        }

        return list;
    }

    private File getFileLeafPath(TreeItem leaf) {

        String header = packType == RESOURCEPACK ? Project.getResourceDir() : Project.getDataDir();

        // TODO: Dateipfad ermitteln
        String path = "";
        String name = (String) leaf.getValue();
        File file;


        file = new File(header + path + name);

        if(file.exists() && file.isFile())
            return file;
        else
            return null;
    }

    private String directoryLabelToDirectory(String label) {
        return directories[findArrayIndex(directoryLabels, label)];
    }

    private int findArrayIndex(Object[] arr, Object obj) {
        for(int i = 0; i < arr.length; i++) {
            if(obj == arr[i]) return i;
        }

        return -1;
    }

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

        System.out.println(root.getChildren().size());
        root.setExpanded(true);
        return root;
    }
}
