package at.tewan.mcide.app.factories;

import at.tewan.mcide.item.Items;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;

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
                img.setImage(Items.getThumbnail(event.getDragboard().getString()));
            }
        });

        populate();
    }

    public void setImage(String itemName) {
        img.setImage(Items.getThumbnail(itemName));
    }

    private void populate() {
        img = new ImageView();

        getChildren().add(img);
        img.fitWidthProperty().bind(widthProperty());
        img.fitHeightProperty().bind(heightProperty());
        img.setSmooth(false);
    }

    private boolean acceptDrop(DragEvent event) {
        return event.getDragboard().hasString() && Items.contains(event.getDragboard().getString());
    }

}
