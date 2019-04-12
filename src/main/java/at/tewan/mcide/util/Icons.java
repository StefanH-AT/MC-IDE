package at.tewan.mcide.util;

import at.tewan.mcide.Resources;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Icons {

    public static ImageView getIcon(String name) {
        return new ImageView(new Image(Resources.getLocalResource("img/icons/" + name + ".png")));
    }
}
