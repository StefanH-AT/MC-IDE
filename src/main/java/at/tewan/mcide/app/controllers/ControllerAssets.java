package at.tewan.mcide.app.controllers;


import java.net.URL;
import java.util.ResourceBundle;

public class ControllerAssets extends ControllerBrowser {


    public ControllerAssets() {
        super("Assets",
                ControllerBrowser.RESOURCEPACK,
                new String[]{"textures", "models", "sounds", "lang"},
                new String[]{"Textures", "Models", "Sounds", "Languages"});
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}
