package at.tewan.mcide.app;

import javafx.scene.input.ScrollEvent;
import org.fxmisc.richtext.LineNumberFactory;

public class FeaturedCodeArea extends SyntaxArea {

    private int fontSize = 14;

    public FeaturedCodeArea(String syntaxName) {
        super(syntaxName);

        setParagraphGraphicFactory(LineNumberFactory.get(this));
        addEventFilter(ScrollEvent.ANY, event -> {
            if(event.isControlDown()) {
                changeFontSize(event.getDeltaY() > 0);
            }
        });

    }

    private void changeFontSize(boolean increase) {

        float zoomFactor = 1.2f;
        int minZoom = 10;
        int maxZoom = 60;

        if(increase) {
            fontSize *= zoomFactor;
        } else {
            fontSize /= zoomFactor;
        }

        fontSize = Math.min(Math.max(fontSize, minZoom), maxZoom);

        setStyle("-fx-font-size: " + fontSize);
    }
}
