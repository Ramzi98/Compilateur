package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;

public class WhileNodeImpl extends ASTNodeBreakpoint implements WhileNode {
    private final MiniJajaNode expression;
    private final InstrsNode instrs;

    public WhileNodeImpl(int line, int column, Breakpoint bp, MiniJajaNode expression, InstrsNode instrs) {
        super(line, column, bp);
        this.expression = expression;
        this.instrs = instrs;
    }

    @Override
    public MiniJajaNode expression() {
        return this.expression;
    }

    @Override
    public InstrsNode instrs() {
        return this.instrs;
    }

    @Override
    public MiniJajaNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0 : return this.expression;
            case 1 : return this.instrs;
            default: throw new IndexOutOfBoundsException();
        }
    }
}
