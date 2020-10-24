package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;

public class WhileNodeImpl extends ASTNodeBreakpoint implements WhileNode {
    private final ASTNode expression;
    private final InstrsNode instrs;

    public WhileNodeImpl(int line, int column, Breakpoint bp, ASTNode expression, InstrsNode instrs) {
        super(line, column, bp);
        this.expression = expression;
        this.instrs = instrs;
    }

    @Override
    public ASTNode expression() {
        return this.expression;
    }

    @Override
    public InstrsNode instrs() {
        return this.instrs;
    }

    @Override
    public ASTNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0 : return this.expression;
            case 1 : return this.instrs;
            default: throw new IndexOutOfBoundsException();
        }
    }
}
