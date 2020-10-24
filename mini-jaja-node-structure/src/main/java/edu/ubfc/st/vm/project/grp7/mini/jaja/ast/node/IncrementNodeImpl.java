package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;

public class IncrementNodeImpl extends ASTNodeBreakpoint implements IncrementNode {
    private final ASTNode identifier;

    public IncrementNodeImpl(int line, int column, Breakpoint bp, ASTNode identifier) {
        super(line, column, bp);
        this.identifier = identifier;
    }

    @Override
    public ASTNode identifier() {
        return this.identifier;
    }

    @Override
    public ASTNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0 : return this.identifier;
            default: throw new IndexOutOfBoundsException();
        }
    }
}
