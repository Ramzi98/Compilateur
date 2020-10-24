package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;

public class MainNodeImpl extends ASTNodeBreakpoint implements MainNode {
    private final VarsNode vars;
    private final InstrsNode instrs;

    public MainNodeImpl(int line, int column, Breakpoint bp, VarsNode vars, InstrsNode instrs) {
        super(line, column, bp);
        this.vars = vars;
        this.instrs = instrs;
    }

    @Override
    public VarsNode vars() {
        return this.vars;
    }

    @Override
    public InstrsNode instrs() {
        return this.instrs;
    }

    @Override
    public ASTNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0 : return this.vars;
            case 1 : return this.instrs;
            default: throw new IndexOutOfBoundsException();
        }
    }
}
