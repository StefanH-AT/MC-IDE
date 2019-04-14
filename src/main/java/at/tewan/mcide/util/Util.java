package at.tewan.mcide.util;

import java.io.File;
import java.util.ArrayList;

public class Util {

    /**
     * @param rootDir Ordner bei dem die Suche gestartet werden soll
     * @param list ArrayList die befüllt werden soll
     * @return ArrayList von Subdirectories
     */
    public static ArrayList<File> getSubDirectories(File rootDir, ArrayList<File> list) {

        for(File f : rootDir.listFiles()) {
            if(f.isDirectory()) {
                getSubDirectories(f, list);
            } else if(f.isFile()) {
                list.add(f);
            }
        }

        return list;
    }

}
