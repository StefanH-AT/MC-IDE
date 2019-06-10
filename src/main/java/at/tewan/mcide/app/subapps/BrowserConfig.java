package at.tewan.mcide.app.subapps;

import at.tewan.mcide.enums.PackType;

public class BrowserConfig {
    private PackType searchedPack;
    private String searchedFolder;

    public BrowserConfig(PackType searchedPack, String searchedFolder) {
        this.searchedPack = searchedPack;
        this.searchedFolder = searchedFolder;
    }

    public PackType getSearchedPack() {
        return searchedPack;
    }

    public String getSearchedFolder() {
        return searchedFolder;
    }
}