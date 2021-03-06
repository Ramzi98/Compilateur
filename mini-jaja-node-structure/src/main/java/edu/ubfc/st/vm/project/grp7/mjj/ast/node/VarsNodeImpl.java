package edu.ubfc.st.vm.project.grp7.mjj.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mjj.ast.MiniJajaNode;

public class VarsNodeImpl extends ASTNodeBreakpoint implements VarsNode {
    private final MiniJajaNode var;
    private final VarsNode vars;

    public VarsNodeImpl(int line, int column, Breakpoint bp, MiniJajaNode var, VarsNode vars) {
        super(line, column, bp);
        this.var = var;
        this.vars = vars;
    }

    @Override
    public MiniJajaNode var() {
        return this.var;
    }

    @Override
    public VarsNode vars() {
        return this.vars;
    }

    @Override
    public MiniJajaNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0 : return this.var;
            case 1 : return this.vars;
            default: throw new IndexOutOfBoundsException();
        }
    }
}
