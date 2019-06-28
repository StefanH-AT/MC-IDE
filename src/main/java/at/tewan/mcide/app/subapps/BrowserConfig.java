package at.tewan.mcide.app.subapps;

import at.tewan.mcide.enums.PackType;

public class BrowserConfig {
    private PackType searchedPack;
    private String[] searchedFolders;

    public BrowserConfig(PackType searchedPack, String[] searchedFolders) {
        this.searchedPack = searchedPack;
        this.searchedFolders = searchedFolders;
    }

    public PackType getSearchedPack() {
        return searchedPack;
    }

    public String[] getSearchedFolders() {
        return searchedFolders;
    }
}