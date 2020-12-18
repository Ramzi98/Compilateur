package edu.ubfc.st.vm.project.grp7.graphic;

import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;

public interface Pattern {
     static final String[] KEYWORDS = new String[] {
            "class", "else","final","if",  "while","main", "write" , "writeln",
            "push","new","newarray", "ainc","swap","pop","jcstop","init","add"
            ,"mul","div", "sub", "load", "return","goto"
    };

     static final String[] TYPE = new String[] {
            "boolean","int", "void","entier","booléen"
    };

     static final String KEYWORD_PATTERN = "\\b(" + String.join("|", KEYWORDS) + ")\\b";
     static final String KEYWORD_TYPE_PATTERN = "\\b(" + String.join("|", TYPE) + ")\\b";
     static final String PAREN_PATTERN = "\\(|\\)";
     static final String BRACE_PATTERN = "\\{|\\}";
     static final String SEMICOLON_PATTERN = "\\;";
     static final String STRING_PATTERN = "\"([^\"\\\\]|\\\\.)*\"";
     static final String COMMENT_PATTERN = "//[^\n]*" + "|" + "/\\*(.|\\R)*?\\*/";

     static final java.util.regex.Pattern PATTERN = java.util.regex.Pattern.compile(
            "(?<KEYWORD>" + KEYWORD_PATTERN + ")"
                    + "|(?<PAREN>" + PAREN_PATTERN + ")"
                    + "|(?<TYPE>" + KEYWORD_TYPE_PATTERN + ")"
                    + "|(?<BRACE>" + BRACE_PATTERN + ")"
                    + "|(?<SEMICOLON>" + SEMICOLON_PATTERN + ")"
                    + "|(?<STRING>" + STRING_PATTERN + ")"
                    + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
    );

    public static StyleSpans<Collection<String>> computeHighlighting(String text) {
        Matcher matcher = Pattern.PATTERN.matcher(text);
        int lastKwEnd = 0;
        StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();
        while (matcher.find()) {
            String styleClass =
                    matcher.group("KEYWORD") != null ? "keyword" :
                            matcher.group("TYPE") != null ? "keywordType" :
                                    matcher.group("PAREN") != null ? "paren" :
                                            matcher.group("BRACE") != null ? "brace" :
                                                    matcher.group("SEMICOLON") != null ? "semicolon" :
                                                            matcher.group("STRING") != null ? "string" :
                                                                    matcher.group("COMMENT") != null ? "comment" :
                                                                            null; /* never happens */ assert styleClass != null;
            spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
            spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
            lastKwEnd = matcher.end();
        }
        spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
        return spansBuilder.create();
    }
    
}
