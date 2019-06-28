package at.tewan.mcide.app.subapps;

import at.tewan.mcide.Resources;
import at.tewan.mcide.app.controls.FeaturedCodeArea;
import at.tewan.mcide.app.subapp.BrowserApplication;
import at.tewan.mcide.app.subapp.SubApplication.SubApp;
import at.tewan.mcide.app.subapp.SubApplicationContent;
import at.tewan.mcide.enums.PackType;
import at.tewan.mcide.mcfunction.Syntax;
import com.google.gson.Gson;
import javafx.scene.Parent;
import javafx.scene.control.TabPane;

import java.io.InputStreamReader;

@SubApp
public class AppFunctions extends BrowserApplication {

    private Syntax syntax;

    public AppFunctions() {
        super("Functions", PackType.DATAPACK, "functions");
    }
    @Override
    public void save() {
        System.out.println("Speichern");
    }

    @Override
    public Class<? extends SubApplicationContent> getApplicationContent() {
        return AppFunctionsContent.class;
    }

    public class AppFunctionsContent implements SubApplicationContent {

        private FeaturedCodeArea codeArea;

        @Override
        public void onCreate() {
            codeArea = new FeaturedCodeArea(new Gson().fromJson(new InputStreamReader(Resources.getResource("syntax/mcfunction.json")), Syntax.class)); // TODO: WARUM IST DAS IN EINER FUCKING ZEILE CMON
        }

        @Override
        public Parent getRoot() {
            return codeArea;
        }

        @Override
        public String getContent() {
            return null;
        }


    }



}
