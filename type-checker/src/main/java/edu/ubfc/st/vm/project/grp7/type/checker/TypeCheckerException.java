package edu.ubfc.st.vm.project.grp7.type.checker;

public class TypeCheckerException extends Exception{
    private final int line;
    private final int column;
    private final String message;

    public TypeCheckerException(int line, int column, String message){
        super(message);
        this.message = message;
        this.line = line;
        this.column = column;
    }

    public TypeCheckerException(Exception e){
        super(e);
        this.message = e.getMessage();
        this.line = -1;
        this.column = -1;
    }

    public int line() {
        return this.line;
    }

    public int column() {
        return this.column;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
