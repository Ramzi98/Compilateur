package edu.ubfc.st.vm.project.grp7.ast;

public abstract class ASTNodeBreakpoint extends ASTNodeWithInfo {
    public ASTNodeBreakpoint(int line, int column, Breakpoint bp) {
        super(line, column);
        this.breakpoint = bp;
    }

    private final Breakpoint breakpoint;

    public final Breakpoint breakpoint() {
        return this.breakpoint;
    }
}
