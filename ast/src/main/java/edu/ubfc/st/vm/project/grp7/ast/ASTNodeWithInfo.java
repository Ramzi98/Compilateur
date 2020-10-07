package edu.ubfc.st.vm.project.grp7.ast;

public abstract class ASTNodeWithInfo implements ASTNode {
    private final int line;
    private final int column;

    public ASTNodeWithInfo(int line, int column){
        this.line = line;
        this.column = column;
    }

    @Override
    public final int line() {
        return this.line;
    }

    @Override
    public final int column() {
        return this.column;
    }
}
