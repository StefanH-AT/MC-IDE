package at.tewan.mcide;

import at.tewan.mcide.app.MainApplication;
import at.tewan.mcide.util.StartParameters;

public class Main {
    public static void main(String[] args) {
        StartParameters.resolveStartArgs(args);

        MainApplication app = new MainApplication(args);

    }
}