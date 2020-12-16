package edu.ubfc.st.vm.project.grp7.ast;

public class IllFormedNodeException extends Exception {
    private final int line;
    private final int column;
    private final String message;

    public IllFormedNodeException(int line, int column, String message){
        super(String.format("[%d,%d] : %s", line, column, message));
        this.message = message;
        this.line = line;
        this.column = column;
    }

    public IllFormedNodeException(String message){
        this(-1, -1, message);
    }

    public int line() {
        return this.line;
    }

    public int column() {
        return this.column;
    }

    @Override
    public String getMessage() {
        return "line : "+line+" column : "+column+" "+message;
    }
}
