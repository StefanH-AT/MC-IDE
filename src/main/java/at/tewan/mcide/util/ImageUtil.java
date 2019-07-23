package at.tewan.mcide.util;

import at.tewan.mcide.Resources;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;


/**
 * When loading an image, it gets saved into an index.
 * If the same image is loaded a second time, instead of creating a new Image instance, the previously created Image from the index gets returned
 *
 */
public class ImageUtil {

    private static HashMap<String, Image> imageIndex = new HashMap<>();
    private static HashMap<Image, ImageView> imageViewIndex = new HashMap<>();


    /**
     * @return Image für ein Icon, welches aus "img/icons/" geladen wird. (.png wird angehängt)
     * */
    public static Image getIcon(String name) {
        return getImage("icons/" + name);
    }

    /**
     * @return Image für ein Bild, welches aus "img/" geladen wird. (.png wird angehängt)
     * */
    public static Image getImage(String name) {
        if(!imageIndex.keySet().contains(name)) {
            imageIndex.put(name, new Image(Resources.getResource("img/" + name + ".png")));
            System.out.println("Added img " + name + " to image index");
        }

        return imageIndex.get(name);
    }

    public static ImageView asImageView(Image img) {
        if(!imageViewIndex.keySet().contains(img)) {
            imageViewIndex.put(img, new ImageView(img));
        }

        return imageViewIndex.get(img);
    }

    public static ImageView getImageView(String name) {
        return asImageView(getImage(name));
    }
}
