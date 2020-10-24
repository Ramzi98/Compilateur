package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;

public class ListExpNodeImpl extends ASTNodeBreakpoint implements ListExpNode {
    private final MiniJajaNode expression;
    private final ListExpNode listexp;

    public ListExpNodeImpl(int line, int column, Breakpoint bp, MiniJajaNode expression, ListExpNode listexp) {
        super(line, column, bp);
        this.expression = expression;
        this.listexp = listexp;
    }

    @Override
    public MiniJajaNode expression() {
        return this.expression;
    }

    @Override
    public ListExpNode listexp() {
        return this.listexp;
    }

    @Override
    public MiniJajaNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0 : return this.expression;
            case 1 : return this.listexp;
            default: throw new IndexOutOfBoundsException();
        }
    }
}
