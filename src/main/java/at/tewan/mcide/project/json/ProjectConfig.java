package at.tewan.mcide.project.json;

import java.util.ArrayList;

public class ProjectConfig {

    private String name;
    private String author;
    private String worldname;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public ArrayList<String> getNamespaces() {
        return namespaces;
    }

    public void setNamespaces(ArrayList<String> namespaces) {
        this.namespaces = namespaces;
    }

    private ArrayList<String> namespaces;

    public String getWorldname() {
        return worldname;
    }

    public void setWorldname(String worldname) {
        this.worldname = worldname;
    }
}
