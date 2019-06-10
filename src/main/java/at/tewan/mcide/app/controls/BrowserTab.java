package at.tewan.mcide.app.controls;

import at.tewan.mcide.app.subapp.BrowserApplication;
import at.tewan.mcide.app.subapps.BrowserConfig;
import at.tewan.mcide.project.Project;
import at.tewan.mcide.util.Icons;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.io.File;

public class BrowserTab extends Tab {

    private TreeView<String> treeView;
    private TreeItem<String> treeRoot;
    private BrowserApplication app;
    private BrowserConfig config;
    private String namespace;

    private BrowserTab(BrowserApplication app, String namespace) {
        this.app = app;
        this.config = app.getConfig();
        this.namespace = namespace;
        this.treeView = new TreeView<>();
        this.treeRoot = new TreeItem<>();

        treeView.setRoot(treeRoot);
        treeView.setShowRoot(false);

        setContent(treeView);
        setText(namespace);
        setClosable(false);


        // Das alles brauch ich um ein Click Event vom TreeItem abzufangen...
        treeView.setCellFactory(tree -> {
            BrowserCell cell = new BrowserCell();
            cell.setOnMouseClicked(event -> {
                if(cell.getTreeItem() instanceof BrowserFileItem)
                    app.openFile(((BrowserFileItem) cell.getTreeItem()).getFile());
            });

            return cell;
        });
    }

    public static BrowserTab[] getBrowserTabsForAllNamespaces(BrowserApplication app) {
        String[] namespaces = Project.getNamespaces();
        BrowserTab[] tabs = new BrowserTab[namespaces.length];

        for(int i = 0; i < namespaces.length; i++) {
            tabs[i] = new BrowserTab(app, namespaces[i]);
        }

        return tabs;
    }

    public void refresh(String rootDir) {
        refresh(new File(rootDir + namespace + "/" + config.getSearchedFolder() + "/"), treeRoot);
    }

    /**
     *
     * Jeder mag Rekursionen.
     * @param currentDir Wird für die Rekursion benötigt
     */
    public void refresh(File currentDir, TreeItem<String> currentItem) {

        // Jetziges Directory hinzufügen
        TreeItem<String> currentDirItem = new TreeItem(currentDir.getName());
        currentDirItem.setExpanded(true);
        currentItem.getChildren().add(currentDirItem);

        for(File currentFile : currentDir.listFiles()) {

            if(currentFile.isDirectory()) {
                refresh(currentFile, currentDirItem);
            } else if(currentFile.isFile()){
                BrowserFileItem item = new BrowserFileItem(currentFile.getName(), Icons.getIcon("file"));
                item.setFile(currentFile);
                currentDirItem.getChildren().add(item);
            }
        }

    }

    public TreeView getTreeView() {
        return treeView;
    }

    private class BrowserFileItem extends TreeItem<String> {

        private File file;

        public BrowserFileItem(String value) {
            super(value);
        }

        public BrowserFileItem(String value, Node graphic) {
            super(value, graphic);
        }

        public void setFile(File file) {
            this.file = file;
        }

        public File getFile() {
            return file;
        }
    }

    private class BrowserCell extends TreeCell<String> {
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if(empty)
                setText(null);
            else
                setText(item);
        }
    }
}
