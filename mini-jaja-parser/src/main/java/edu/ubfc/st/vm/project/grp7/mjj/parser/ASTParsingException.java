package edu.ubfc.st.vm.project.grp7.mjj.parser;

public class ASTParsingException extends RuntimeException {
    private final int line;
    private final int column;
    private final String message;

    public ASTParsingException(int line, int column, String message) {
        super(String.format("[%d,%d] : %s", line, column, message));
        this.line = line;
        this.column = column;
        this.message = message;
    }

    public int line() {
        return line;
    }

    public int column() {
        return column;
    }

    @Override
    public String getMessage(){
        return "line "+this.line+":"+this.column+" no viable alternative at input '"+this.message+"'";
    }
}
