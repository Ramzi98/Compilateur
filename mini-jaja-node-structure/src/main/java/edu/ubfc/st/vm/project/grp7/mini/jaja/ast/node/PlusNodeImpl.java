package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;

public class PlusNodeImpl extends ASTNodeBreakpoint implements PlusNode {
    private final ASTNode leftOperand;
    private final ASTNode rightOperand;

    public PlusNodeImpl(int line, int column, Breakpoint bp, ASTNode leftOperand, ASTNode rightOperand) {
        super(line, column, bp);
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    @Override
    public ASTNode leftOperand() {
        return this.leftOperand;
    }

    @Override
    public ASTNode rightOperand() {
        return this.rightOperand;
    }

    @Override
    public ASTNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0 : return this.leftOperand;
            case 1 : return this.rightOperand;
            default: throw new IndexOutOfBoundsException();
        }
    }
}
