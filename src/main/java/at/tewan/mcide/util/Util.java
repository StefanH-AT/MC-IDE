package at.tewan.mcide.util;

import java.io.File;
import java.util.ArrayList;

public class Util {

    /**
     * @param rootDir Ordner bei dem die Suche gestartet werden soll
     * @param list ArrayList die befüllt werden soll
     * @return ArrayList von Subdirectories
     * Durchsucht rekursiv ein Verzeichnis und fügt alle Dateien einer liste zu.
     * (Einfach eine leere neue ArrayList übergeben)
     */
    public static ArrayList<File> getFilesOfDirectory(File rootDir, ArrayList<File> list) {

        for(File f : rootDir.listFiles()) {
            if(f.isDirectory()) {
                getFilesOfDirectory(f, list);
            } else if(f.isFile()) {
                list.add(f);
            }
        }

        return list;
    }

}
