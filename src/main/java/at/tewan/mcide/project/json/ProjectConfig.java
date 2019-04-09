package at.tewan.mcide.project.json;

import java.util.ArrayList;

public class ProjectConfig {

    private String name;
    private String author;
    private int version;

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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public ArrayList<String> getNamespaces() {
        return namespaces;
    }

    public void setNamespaces(ArrayList<String> namespaces) {
        this.namespaces = namespaces;
    }

    private ArrayList<String> namespaces;

}
