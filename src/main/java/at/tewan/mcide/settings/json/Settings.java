package at.tewan.mcide.settings.json;

import java.util.ArrayList;

public class Settings {

    private String mcDir;
    private String theme;
    private ArrayList<String> recentProjects;

    public String getMcDir() {
        return mcDir;
    }

    public void setMcDir(String mcDir) {
        this.mcDir = mcDir;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public ArrayList<String> getRecentProjects() {
        return recentProjects;
    }

    public void setRecentProjects(ArrayList<String> recentProjects) {
        this.recentProjects = recentProjects;
    }
}
