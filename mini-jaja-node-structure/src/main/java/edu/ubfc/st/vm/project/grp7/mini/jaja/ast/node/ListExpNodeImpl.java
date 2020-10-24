package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;

public class ListExpNodeImpl extends ASTNodeBreakpoint implements ListExpNode {
    private final ASTNode expression;
    private final ListExpNode listexp;

    public ListExpNodeImpl(int line, int column, Breakpoint bp, ASTNode expression, ListExpNode listexp) {
        super(line, column, bp);
        this.expression = expression;
        this.listexp = listexp;
    }

    @Override
    public ASTNode expression() {
        return this.expression;
    }

    @Override
    public ListExpNode listexp() {
        return this.listexp;
    }

    @Override
    public ASTNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0 : return this.expression;
            case 1 : return this.listexp;
            default: throw new IndexOutOfBoundsException();
        }
    }
}
