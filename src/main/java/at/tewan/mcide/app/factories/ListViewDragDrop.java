package at.tewan.mcide.app.factories;

import javafx.scene.control.ListView;
import javafx.scene.input.TransferMode;

public class ListViewDragDrop extends ListView<String> {

    protected boolean dragFrom = true;
    protected boolean acceptDrops = true;
    protected boolean removeOnDrag = true;

    public ListViewDragDrop() {
        setOnDragOver(event -> {

            if(event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.ANY);
            }

            event.consume();
        });

        setOnDragEntered(event -> {
            if(event.getDragboard().hasString())
                getStyleClass().add("drag-over");

            event.consume();
        });

        setOnDragExited(event -> {
            if(event.getDragboard().hasString()) {
                getStyleClass().remove("drag-over");
            }
        });
    }
}
