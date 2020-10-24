package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;

public class VarsNodeImpl extends ASTNodeBreakpoint implements VarsNode {
    private final VarNode var;
    private final VarsNode vars;

    public VarsNodeImpl(int line, int column, Breakpoint bp, VarNode var, VarsNode vars) {
        super(line, column, bp);
        this.var = var;
        this.vars = vars;
    }

    @Override
    public VarNode var() {
        return this.var;
    }

    @Override
    public VarsNode vars() {
        return this.vars;
    }

    @Override
    public ASTNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0 : return this.var;
            case 1 : return this.vars;
            default: throw new IndexOutOfBoundsException();
        }
    }
}
