package at.tewan.mcide.app.controllers;

import at.tewan.mcide.Resources;
import at.tewan.mcide.mcfunction.Syntax;
import at.tewan.mcide.mcfunction.SyntaxPattern;
import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.ScrollEvent;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import java.io.InputStreamReader;
import java.net.URL;
import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControllerFunctions extends ControllerBrowser {

    private CodeArea code;
    private Pattern mcFunctionPattern;
    private Syntax mcFunctionSyntax;


    @FXML
    private SplitPane splitPane;

    // Standard Font
    private int fontSize = 14;

    public ControllerFunctions() {
        super("Functions", ControllerBrowser.DATAPACK, new String[]{"functions"}, new String[]{"Functions"});
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        code = new CodeArea();
        splitPane.getItems().add(code);

        code.getStylesheets().add("javafx/mcfunctions.css");
        code.setParagraphGraphicFactory(LineNumberFactory.get(code));

        code.addEventFilter(ScrollEvent.ANY, event -> {
            if(event.isControlDown()) {
                changeFontSize(event.getDeltaY() > 0);
            }
        });

        // ===================== SYNTAX HIGHLIGHTING =====================================================

        // Read the json syntax file
        Gson json = new Gson();
        mcFunctionSyntax = json.fromJson(new InputStreamReader(Resources.getLocalResource("json/mcfunction_syntax.json")), Syntax.class);

        // Build massive regex
        String regex = "";
        for(SyntaxPattern pattern : mcFunctionSyntax.getSyntax()) {
            regex += "(?<" + pattern.getName().toUpperCase() + ">" + pattern.getPattern() + ")|";
        }

        // Remove last | symbol to close the alternation
        regex = regex.substring(0, regex.length() - 1);

        // Compiling the regex
        mcFunctionPattern = Pattern.compile(regex);

        code.multiPlainChanges().successionEnds(Duration.ofMillis(200)).subscribe(ignore -> {
            code.setStyleSpans(0, updateSyntaxHighlighting());
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

        code.setStyle("-fx-font-size: " + fontSize);
        System.out.println(code.getCurrentParagraph());
    }

    private StyleSpans<Collection<String>> updateSyntaxHighlighting() {

        String text = code.getText();

        Matcher matcher = mcFunctionPattern.matcher(text);
        int lastKwEnd = 0;
        StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();

        while(matcher.find()) {
            String styleClass = "";

            for(SyntaxPattern syntax : mcFunctionSyntax.getSyntax()) {

                if(matcher.group(syntax.getName().toUpperCase()) != null) {
                    styleClass = syntax.getName();
                }

            }

            spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
            spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
            lastKwEnd = matcher.end();
        }

        spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
        return spansBuilder.create();
    }



}
