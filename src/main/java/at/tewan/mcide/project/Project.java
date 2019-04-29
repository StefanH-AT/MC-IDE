package at.tewan.mcide.project;

import at.tewan.mcide.Resources;
import at.tewan.mcide.mcfiles.PackDefinition;
import at.tewan.mcide.project.json.ProjectConfig;
import at.tewan.mcide.versions.Version;
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

    private static PackDefinition resourceDefinition, dataDefinition;

    public static void newProject(String name, String author, int version, String... namespaces) {
        currentProject = new ProjectConfig();
        currentProject.setName(name);
        currentProject.setAuthor(author);
        currentProject.setVersion(version);
        currentProject.setNamespaces(new ArrayList<>(Arrays.asList(namespaces)));

        String label = "Project: " + name + " By: " + author + " \nMade with MC-IDE";
        resourceDefinition = new PackDefinition(label, Version.getResourcePackFormat(version));
        dataDefinition = new PackDefinition(label, Version.getDataPackFormat(version));

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

            String namespaceDataDir = "data/" + namespace + "/";
            String namespaceResourceDir = "res/" + namespace + "/";

            System.out.println(namespaceDataDir);

            createDir(namespaceDataDir);
            createDir(namespaceResourceDir);

            createDir(namespaceDataDir + "functions/");
            createDir(namespaceDataDir + "tags/");
            createDir(namespaceDataDir + "recipes/");
            createDir(namespaceDataDir + "functions/");
            createDir(namespaceDataDir + "loottables/");
            createDir(namespaceDataDir + "advancements/");

            createDir(namespaceResourceDir + "textures/");
            createDir(namespaceResourceDir + "sounds/");
            createDir(namespaceResourceDir + "lang/");
            createDir(namespaceResourceDir + "models/");
        }

        // ================= PROJECT SETTING DATEI ======================

        FileWriter projectConfigWriter = new FileWriter(getProjectConfig());
        projectConfigWriter.write(gson.toJson(currentProject));
        projectConfigWriter.close();

        File dataDefinitionFile = new File(getDataDir() + "pack.mcmeta");
        File resourceDefinitionFile = new File(getResourceDir() + "pack.mcmeta");

        FileWriter dataFileWriter = new FileWriter(dataDefinitionFile);
        FileWriter resourceFileWriter = new FileWriter(resourceDefinitionFile);

        dataFileWriter.write(gson.toJson(dataDefinition));
        resourceFileWriter.write(gson.toJson(resourceDefinition));

        dataFileWriter.close();
        resourceFileWriter.close();


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
        return getProjectDir() + "project.definitions";
    }

    public static String getProjectDir() {
        if(currentProject == null) return null;
        return Resources.getWorkspaceDir() + currentProject.getName() + "/";
    }

    public static String getResourceDir() {
        return getProjectDir() + "res/";
    }

    public static String getDataDir() {
        return getProjectDir() + "data/";
    }

    public static String getNamespaceResource(String namespace) {
        return getResourceDir() + namespace + "/";
    }

    public static String getNamespaceData(String namespace) {
        return getDataDir() + namespace + "/";
    }

    public static String getNamespaceFunctions(String namespace) {
        return getNamespaceData(namespace) + "functions/";
    }
}
