package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;

public class NotNodeImpl extends ASTNodeBreakpoint implements NotNode {
    private final MiniJajaNode expression;

    public NotNodeImpl(int line, int column, Breakpoint bp, MiniJajaNode expression) {
        super(line, column, bp);
        this.expression = expression;
    }

    @Override
    public MiniJajaNode expression() {
        return this.expression;
    }

    @Override
    public MiniJajaNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0 : return this.expression;
            default: throw new IndexOutOfBoundsException();
        }
    }
}
