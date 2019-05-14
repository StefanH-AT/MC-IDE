package at.tewan.mcide.util;

/**
 * @author Stefan
 * */

public class StringUtil {

    public static String collect(String... strings) {
        String output = new String();
        for(String s : strings) {
            output += s;
        }

        return output;
    }

}
