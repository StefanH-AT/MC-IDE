package at.tewan.mcide.app.subapp;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

public abstract class SubApplication {

    private Tab tab;
    private SubScene scene;
    private AnchorPane pane;

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

    }

    protected AnchorPane getRoot() {
        return pane;
    }

    protected Parent getArea() {
        return getRoot();
    }

    public Tab getTab() {
        return tab;
    }

    protected void setDisplayName(String name) {
        tab.setText(name);
    }

    public abstract void save();

    public abstract void create();

    @Target(ElementType.TYPE)
    public @interface SubApp {

    }
}
