package at.tewan.mcide.app.controllers;

import at.tewan.mcide.project.Project;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ResourceBundle;

import static java.nio.file.Files.walkFileTree;

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

        browserRoot.getChildren().forEach((namespace) -> {
            TreeItem[] subdirs = new TreeItem[directories.length];
            for(int i = 0; i < subdirs.length; i++) {
                subdirs[i] = new TreeItem<>(directoryLabels[i]);

                try {
                    Path path = new File(
                            rootDir +
                            namespaces[browserRoot.getChildren().indexOf(namespace)] + "/" +
                            directories[i]).toPath();

                        System.out.println(path.toString());

                        walkFileTree(path, new FileVisitor<Path>() {
                        @Override
                        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                            return null;
                        }

                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

                            System.out.println(file.toString());

                            return null;
                        }

                        @Override
                        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                            return null;
                        }

                        @Override
                        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                            return null;
                        }
                    });
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }

            namespace.getChildren().addAll(subdirs);


        });

        browser.setRoot(browserRoot);
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
