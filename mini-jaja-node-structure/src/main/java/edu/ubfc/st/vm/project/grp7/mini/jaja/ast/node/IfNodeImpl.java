package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;

public class IfNodeImpl extends ASTNodeBreakpoint implements IfNode {
    private final ASTNode expression;
    private final InstrsNode trueInstrs;
    private final InstrsNode falseInstrs;

    public IfNodeImpl(int line, int column, Breakpoint bp, ASTNode expression, InstrsNode trueInstrs, InstrsNode falseInstrs) {
        super(line, column, bp);
        this.expression = expression;
        this.trueInstrs = trueInstrs;
        this.falseInstrs = falseInstrs;
    }

    @Override
    public ASTNode expression() {
        return this.expression;
    }

    @Override
    public InstrsNode trueInstrs() {
        return this.trueInstrs;
    }

    @Override
    public InstrsNode falseInstrs() {
        return this.falseInstrs;
    }

    @Override
    public ASTNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0 : return this.expression;
            case 1 : return this.trueInstrs;
            case 2 : return this.falseInstrs;
            default: throw new IndexOutOfBoundsException();
        }
    }
}
