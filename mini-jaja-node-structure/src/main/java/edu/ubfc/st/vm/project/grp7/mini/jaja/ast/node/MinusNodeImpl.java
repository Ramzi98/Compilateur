package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;

public class MinusNodeImpl extends ASTNodeBreakpoint implements MinusNode {
    private final MiniJajaNode expression;

    public MinusNodeImpl(int line, int column, Breakpoint bp, MiniJajaNode expression) {
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
