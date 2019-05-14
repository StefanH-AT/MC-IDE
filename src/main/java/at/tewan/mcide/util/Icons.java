package at.tewan.mcide.util;

import at.tewan.mcide.Resources;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Icons {

    /**
     * @return ImageView für ein Icon, welches aus "img/icons/" geladen wird. (.png wird angehängt)
     * */
    public static ImageView getIcon(String name) {
        return new ImageView(new Image(Resources.getResource("img/icons/" + name + ".png")));
    }
}
