package at.tewan.mcide.project;

import at.tewan.mcide.project.json.ProjectConfig;
import at.tewan.mcide.settings.GlobalSettings;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Project {

    private static ProjectConfig currentProject;
    private static File projectRoot;

    public static void newProject(String name, String author, int version, String... namespaces) {
        currentProject = new ProjectConfig();
        currentProject.setName(name);
        currentProject.setAuthor(author);
        currentProject.setVersion(version);
        currentProject.setNamespaces(new ArrayList<>(Arrays.asList(namespaces)));

        save();
    }

    public static void save() {
        Gson gson = new Gson();

        try {
            projectRoot = new File(getProjectDir());
            if(!projectRoot.exists()) projectRoot.mkdir();

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
        return GlobalSettings.getSettings().getMcDir() + "/ide/" + currentProject.getName() + "/";
    }
}
