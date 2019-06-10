package at.tewan.mcide.util;

import at.tewan.mcide.settings.GlobalSettings;

public class Themes {

    private static final String CSS_LOCATION = "javafx/";

    public static final String DEFAULT = CSS_LOCATION + "default.css";

    public static final String[] themes = {
        "dark",
        "light"
    };

    public static String getCurrentTheme() {
        return getTheme(GlobalSettings.getSettings().getTheme());
    }

    /**
     * @return Interner Pfad zur css Datei. null wenn nicht vorhanden!
     * */
    public static String getTheme(String theme) {
        for(String th : themes) {
            if(theme.equals(th)) return CSS_LOCATION + theme + ".css";
        }

        return null;
    }

}
