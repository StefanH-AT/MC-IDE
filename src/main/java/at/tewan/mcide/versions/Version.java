package at.tewan.mcide.versions;

import java.util.Arrays;
import java.util.Comparator;

public class Version {

    private static int[] supportedVersions = {1132, 1140};

    public static int[] getSupportedVersions() {
        return supportedVersions;
    }

    public static int getNewestVersion() {
        return supportedVersions[supportedVersions.length - 1];
    }

    public static String getVersionAsName(int version) {
        return version / 1000 + "." + version % 1000 / 10 + "." + version % 10;
    }

    public static int getVersionAsId(String version) {
        return version.charAt(0) * 1000 + version.charAt(2) * 100 + version.charAt(3) * 10 + version.charAt(5);
    }

    public static String[] getSupportedVersionsAsString(boolean sortAsc) {
        String[] toReturn = new String[supportedVersions.length];

        for(int i = 0; i < supportedVersions.length; i++) {
            toReturn[i] = getVersionAsName(supportedVersions[i]);
        }

        if(sortAsc) {
            Arrays.sort(toReturn, Comparator.naturalOrder());
        } else {
            Arrays.sort(toReturn, Comparator.reverseOrder());
        }

        return toReturn;
    }

    public static int getDataPackFormat(int version) {
        return 1;
    }

    public static int getResourcePackFormat(int version) {
        if(version <= 1092) return 1;
        if(version <= 1102) return 2;
        if(version <= 1122) return 3;

        return 4;
    }
}
