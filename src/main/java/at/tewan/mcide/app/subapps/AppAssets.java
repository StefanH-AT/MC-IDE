package at.tewan.mcide.app.subapps;

import at.tewan.mcide.app.subapp.BrowserApplication;
import at.tewan.mcide.app.subapp.SubApplication.SubApp;
import at.tewan.mcide.app.subapp.SubApplicationContent;
import at.tewan.mcide.enums.PackType;

@SubApp
public class AppAssets extends BrowserApplication {

    public AppAssets() {
        super("Assets", PackType.RESOURCEPACK, "textures", "models", "sounds");
    }

    @Override
    public Class<? extends SubApplicationContent> getApplicationContent() {
        return null;
    }
}
