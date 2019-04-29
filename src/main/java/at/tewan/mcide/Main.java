package at.tewan.mcide;

import at.tewan.mcide.app.MainApplication;
import at.tewan.mcide.item.Items;
import at.tewan.mcide.mcfunction.command.Commands;
import at.tewan.mcide.settings.GlobalSettings;
import at.tewan.mcide.util.StartParameters;
import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        StartParameters.resolveStartArgs(args);

        GlobalSettings.loadConfig();
        Commands.init();
        Items.init();

        Application.launch(MainApplication.class, args);
    }
}