package at.tewan.mcide.filters;

import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

public class Filters {

    private static final UnaryOperator<TextFormatter.Change> namespaceFilter = (TextFormatter.Change change) -> {

        if(change.isAdded()) {
            if(change.getText().matches("[a-z0-9_ ]")) {
                if(change.getText().equals(" ")) {
                    change.setText("_");
                }

            } else if(change.getText().matches("[A-Z]")) {
                change.setText(change.getText().toLowerCase());
            } else {
                change.setText("");
            }

        }

        return change;

    };

    public static TextFormatter getNamespaceFilter() {
        return new TextFormatter(namespaceFilter);
    }
}
