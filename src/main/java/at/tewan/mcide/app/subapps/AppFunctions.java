package at.tewan.mcide.app.subapps;

import at.tewan.mcide.app.subapp.BrowserApplication;
import at.tewan.mcide.app.subapp.SubApplication.SubApp;
import at.tewan.mcide.app.subapp.SubApplicationContent;
import at.tewan.mcide.app.subapps.content.AppFunctionsContent;
import at.tewan.mcide.enums.PackType;
import at.tewan.mcide.mcfunction.Syntax;

@SubApp
public class AppFunctions extends BrowserApplication {

    private Syntax syntax;

    public AppFunctions() {
        super("Functions", PackType.DATAPACK, "functions");
    }

    @Override
    public Class<? extends SubApplicationContent> getApplicationContent() {
        return AppFunctionsContent.class;
    }

}

