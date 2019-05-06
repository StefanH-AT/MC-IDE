package at.tewan.mcide.app.factories;

import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;

import javax.swing.text.html.ImageView;

public class ItemSlotPane extends Pane {

    private ImageView img;

    public ItemSlotPane() {
        // Blauer Rand an
        setOnDragEntered(event -> {
            if(acceptDrop(event))
                getStyleClass().add("drag-over");
        });

        // Blauer Rand aus
        setOnDragExited(event -> {
            if(acceptDrop(event))
                getStyleClass().remove("drag-over");
        });

        // Accepted Transfer Modes zurückgeben
        setOnDragOver(event -> {
            if(acceptDrop(event))
                event.acceptTransferModes(TransferMode.ANY);
            else
                event.acceptTransferModes(TransferMode.NONE);
        });

        setOnDragDropped(event -> {
            if(acceptDrop(event)) {

            }
        });
    }

    private boolean acceptDrop(DragEvent event) {
        return event.getDragboard().hasString();
    }

}
