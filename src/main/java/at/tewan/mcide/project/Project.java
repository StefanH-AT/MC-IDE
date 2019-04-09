package at.tewan.mcide.project;

import at.tewan.mcide.project.json.ProjectConfig;
import at.tewan.mcide.settings.GlobalSettings;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Project {

    private static ProjectConfig currentProject;

    public static void newProject(String name, String author, int version, String... namespaces) {
        currentProject = new ProjectConfig();
        currentProject.name = name;
        currentProject.author = author;
        currentProject.version = version;
        currentProject.namespaces = new ArrayList<>(Arrays.asList(namespaces));

        save();
    }

    public static void save() {
        Gson gson = new Gson();

        try {
            FileWriter projectConfigWriter = new FileWriter(getProjectConfig());
            projectConfigWriter.write(gson.toJson(currentProject));
            projectConfigWriter.close();

        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    public static String getProjectConfig() {
        return getProjectDir() + "project.json";
    }

    public static String getProjectDir() {
        return GlobalSettings.getSettings().getMcDir() + "/ide/";
    }
}
