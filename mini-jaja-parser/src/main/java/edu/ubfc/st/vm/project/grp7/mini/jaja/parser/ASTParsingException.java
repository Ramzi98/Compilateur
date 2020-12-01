package edu.ubfc.st.vm.project.grp7.mini.jaja.parser;

public class ASTParsingException extends RuntimeException {
    private final int line;
    private final int column;

    public ASTParsingException(int line, int column, String message) {
        super(String.format("[%d,%d] : %s", line, column, message));
        this.line = line;
        this.column = column;
    }

    public int line() {
        return line;
    }

    public int column() {
        return column;
    }
}
