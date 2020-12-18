package edu.ubfc.st.vm.project.grp7.mjj.ast;

import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;

public abstract class MiniJajaOperator extends ASTNodeBreakpoint implements MiniJajaOperatorNode {
    protected final MiniJajaNode leftOperand;
    protected final MiniJajaNode rightOperand;

    public MiniJajaOperator(int line, int column, Breakpoint bp, MiniJajaNode lhs, MiniJajaNode rhs) {
        super(line, column, bp);
        this.leftOperand = lhs;
        this.rightOperand = rhs;
    }

    @Override
    public MiniJajaNode leftOperand() {
        return this.leftOperand;
    }

    @Override
    public MiniJajaNode rightOperand() {
        return this.rightOperand;
    }

    @Override
    public MiniJajaNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0 : return this.leftOperand;
            case 1 : return this.rightOperand;
            default: throw new IndexOutOfBoundsException();
        }
    }
}
