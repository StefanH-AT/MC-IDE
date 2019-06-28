package at.tewan.mcide.util;

import at.tewan.mcide.Resources;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageUtil {

    /**
     * @return ImageView für ein Icon, welches aus "img/icons/" geladen wird. (.png wird angehängt)
     * */
    public static ImageView getIcon(String name) {
        return new ImageView(new Image(Resources.getResource("img/icons/" + name + ".png")));
    }

    /**
     * @return ImageView für ein Icon, welches aus "img/icons/" geladen wird. (.png wird angehängt)
     * */
    public static Image getImage(String name) {
        return new Image(Resources.getResource("img/" + name + ".png"));
    }
}
