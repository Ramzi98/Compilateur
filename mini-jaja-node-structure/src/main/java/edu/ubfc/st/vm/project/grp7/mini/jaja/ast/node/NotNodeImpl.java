package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;

public class NotNodeImpl extends ASTNodeBreakpoint implements NotNode {
    private final ASTNode expression;

    public NotNodeImpl(int line, int column, Breakpoint bp, ASTNode expression) {
        super(line, column, bp);
        this.expression = expression;
    }

    @Override
    public ASTNode expression() {
        return this.expression;
    }

    @Override
    public ASTNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0 : return this.expression;
            default: throw new IndexOutOfBoundsException();
        }
    }
}
