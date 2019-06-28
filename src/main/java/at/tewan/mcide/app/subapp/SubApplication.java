package at.tewan.mcide.app.subapp;

import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

public abstract class SubApplication {

    private Tab tab;
    private AnchorPane pane;

    private Class<? extends SubApplicationContent> subAppContent;

    public SubApplication() {
        this("");
    }

    public SubApplication(String displayName) {

        System.out.println("Loading subapplication '" + displayName + "'");

        pane = new AnchorPane();
        tab = new Tab(displayName);
        tab.setContent(pane);

        pane.setPrefWidth(Long.MAX_VALUE);
        pane.setPrefHeight(Long.MAX_VALUE);

        subAppContent = getApplicationContent();

    }

    public Class<? extends SubApplicationContent> getSubAppContent() {
        return subAppContent;
    }

    protected AnchorPane getRoot() {
        return pane;
    }

    public Tab getTab() {
        return tab;
    }

    public Scene getScene() {
        return tab.getTabPane().getScene(); // TODO: Returnt aus irgendeinem Grund null
    }

    protected void setDisplayName(String name) {
        tab.setText(name);
    }

    public abstract Class<? extends SubApplicationContent> getApplicationContent();

    @Target(ElementType.TYPE)
    public @interface SubApp {

    }
}
