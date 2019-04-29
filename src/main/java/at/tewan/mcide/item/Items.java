package at.tewan.mcide.item;

import at.tewan.mcide.Resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Items {

    private static String[] items;

    public static void init() {

        try {

            //List<String> list = Files.readAllLines(new File("definitions/items_13.txt").toPath());
            BufferedReader reader = new BufferedReader(new InputStreamReader(Resources.getLocalResource("definitions/items_13.txt")));

            List<String> list = new ArrayList<>();
            String line;

            while((line = reader.readLine()) != null) {
                list.add(line);
            }

            items = list.toArray(new String[list.size()]);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static String[] getAllItems() {
        return items;
    }
}
