package at.tewan.mcide.app.factories;

import javafx.scene.control.ListCell;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

public class ListCellDragable extends ListCell<String> {

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        setText(item);
    }

    public ListCellDragable(boolean removeOnDrag) {

        setOnDragDetected(event -> {

            Dragboard dragboard = startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();

            content.putString(getText());
            dragboard.setContent(content);

            event.consume();

            if(removeOnDrag) {
                getListView().getItems().remove(this);
            }

        });

    }
}
