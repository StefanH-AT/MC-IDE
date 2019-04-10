package at.tewan.mcide.project;

import at.tewan.mcide.Resources;
import at.tewan.mcide.mcfiles.PackDefinition;
import at.tewan.mcide.project.json.ProjectConfig;
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

    private static PackDefinition resourceDefinitions, dataDefinitions;

    public static void newProject(String name, String author, int version, String... namespaces) {
        currentProject = new ProjectConfig();
        currentProject.setName(name);
        currentProject.setAuthor(author);
        currentProject.setVersion(version);
        currentProject.setNamespaces(new ArrayList<>(Arrays.asList(namespaces)));

        resourceDefinitions = new PackDefinition();
        dataDefinitions = new PackDefinition();

        save();
    }

    public static void save() {
        Gson gson = new Gson();

        try {

        // ================= ORDNER STRUKTUR ======================
        createDir("");
        createDir("res");
        createDir("data");
        createDir("data/minecraft");
        createDir("data/minecraft/tags");

        for(int i = 0; i < currentProject.getNamespaces().size(); i++) {
            String namespace = currentProject.getNamespaces().get(i);

            String namespaceDataDir = getProjectDir() + "data/" + namespace;
            String namespaceResourceDir = getProjectDir() + "res/" + namespace;

            createDir(namespaceDataDir);
            createDir(namespaceResourceDir);

            File dataDefinition = new File(namespaceDataDir + "/pack.mcmeta");
            File resourceDefinition = new File(namespaceResourceDir + "/pack.mcmeta");

            FileWriter dataFileWriter = new FileWriter(dataDefinition);
            FileWriter resourceFileWriter = new FileWriter(resourceDefinition);

            dataFileWriter.write(gson.toJson(dataDefinitions[i]));
            resourceFileWriter.write(gson.toJson(resourceDefinitions[i]));

            dataFileWriter.close();
            resourceFileWriter.close();
        }

        // ================= PROJECT SETTING DATEI ======================


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
        if(currentProject == null) {
            return new String[0];
        } else {
            return currentProject.getNamespaces().toArray(new String[currentProject.getNamespaces().size()]);
        }
    }

    public static String getProjectConfig() {
        return getProjectDir() + "project.json";
    }

    public static String getProjectDir() {
        return Resources.getWorkspaceDir() + currentProject.getName() + "/";
    }
}
