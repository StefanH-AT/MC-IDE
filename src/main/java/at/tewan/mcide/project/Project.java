package at.tewan.mcide.project;

import at.tewan.mcide.project.json.ProjectConfig;
import at.tewan.mcide.settings.GlobalSettings;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
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

        // ================= ORDNER STRUKTUR ======================
        createDir("");
        createDir("res");
        createDir("data");
        createDir("data/minecraft");
        createDir("data/minecraft/tags");

        for(String namespace : currentProject.getNamespaces()) {
            createDir("data/" + namespace);
            createDir("res/" + namespace);
        }

        // ================= PROJECT SETTING DATEI ======================
        try {

            FileWriter projectConfigWriter = new FileWriter(getProjectConfig());
            projectConfigWriter.write(gson.toJson(currentProject));
            projectConfigWriter.close();



        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    public static void load(String configFile) {
        Gson gson = new Gson();

        try {
            ProjectConfig cfg = gson.fromJson(new FileReader(configFile), ProjectConfig.class);
            currentProject = cfg;
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private static void createDir(String name) {
        File f = new File(getProjectDir() + name);
        if(!f.exists()) f.mkdir();
    }

    public static String[] getNamespaces() {
        return currentProject.getNamespaces().toArray(new String[currentProject.getNamespaces().size()]);
    }

    public static String getProjectConfig() {
        return getProjectDir() + "project.json";
    }

    public static String getProjectDir() {
        return GlobalSettings.getSettings().getMcDir() + "/ide/" + currentProject.getName() + "/";
    }
}
