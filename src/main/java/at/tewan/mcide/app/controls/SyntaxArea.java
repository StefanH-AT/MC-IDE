package at.tewan.mcide.app.controls;

import at.tewan.mcide.Resources;
import at.tewan.mcide.mcfunction.Syntax;
import at.tewan.mcide.mcfunction.SyntaxPattern;
import com.google.gson.Gson;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import java.io.InputStreamReader;
import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SyntaxArea extends CodeArea {

    protected Pattern pattern;
    protected Syntax syntax;

    public SyntaxArea(Syntax syntax) {
        this.syntax = syntax;

        System.out.println(syntax.getSyntaxPatterns().size());

        getStylesheets().add("syntax/mcfunction.css");

        String regex = "";
        for(SyntaxPattern pattern : syntax.getSyntaxPatterns()) {
            regex += "(?<" + pattern.getName().toUpperCase() + ">" + pattern.getPattern() + ")|";
        }

        // Remove last | symbol to close the alternation
        regex = regex.substring(0, regex.length() - 1);

        // Compiling the regex
        pattern = Pattern.compile(regex);

        multiPlainChanges().successionEnds(Duration.ofMillis(200)).subscribe(ignore ->
            setStyleSpans(0, updateSyntaxHighlighting())
        );
    }

    private StyleSpans<Collection<String>> updateSyntaxHighlighting() {

        String text = getText();

        Matcher matcher = pattern.matcher(text);
        int lastKwEnd = 0;
        StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();

        while(matcher.find()) {
            String styleClass = "";

            for(SyntaxPattern syntax : syntax.getSyntaxPatterns()) {

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
