package edu.ubfc.st.vm.project.grp7.graphic;

import org.fxmisc.richtext.CodeArea;
import org.reactfx.Subscription;

import java.time.Duration;
import java.util.Optional;

public interface Pattern {
     static final String[] KEYWORDS = new String[] {
            "class", "else","final","if",  "while","main", "write" , "writeln",
            "push","new","newarray", "ainc","swap","pop","jcstop","init","add"
            ,"mul","div", "sub", "load"
    };

     static final String[] TYPE = new String[] {
            "boolean","int", "void","entier"
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

}
