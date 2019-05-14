package at.tewan.mcide.item;

import at.tewan.mcide.Resources;
import javafx.scene.image.Image;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Items {

    private static String[] items;
    private static Image[] itemThumbnails;

    public static void init() {

        try {

            //List<String> list = Files.readAllLines(new File("definitions/items_13.txt").toPath());
            BufferedReader reader = new BufferedReader(new InputStreamReader(Resources.getResource("definitions/items_13.txt")));

            List<String> list = new ArrayList<>();
            String line;

            while((line = reader.readLine()) != null) {
                list.add(line);
            }

            items = list.toArray(new String[list.size()]);

            itemThumbnails = new Image[items.length];

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static String[] getAllItems() {
        return items;
    }

    public static boolean contains(String item) {
        for(String i : items) {
            if(i.equals(item)) {
                return true;
            }
        }

        return false;
    }

    // Es werden nicht alle Bilder im init in den Ram geladen, damit das Programm schneller startet und um
    // Arbeitsspeicher zu sparen. Die Bilder werden nach Bedarf erstellt und in das Array unter gleichem
    // Index des dazugehörigen Item Namens im Item array gespeichert. Wenn das Bild dann noch einmal geladen wird,
    // wird kein neues erstellt, sondern das bereits bestehende aus dem Array genommen.
    public static Image getThumbnail(String item) {
        int index = 0;
        for(int i = 0; i < items.length; i++) {
            if(item.equals(items[i])) {
                index = i;
                break;
            }
        }

        // Bild ist noch nicht im Array => Neues erstellen
        if(itemThumbnails[index] == null) {
            itemThumbnails[index] = new Image(Resources.getResource("img/items/" + item + ".png"));
        }

        return itemThumbnails[index];
    }
}
