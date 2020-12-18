package edu.ubfc.st.vm.project.grp7.jjc.ast;

import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;

public abstract class JajaCodeOperator extends ASTNodeWithInfo implements JajaCodeOperatorNode{

    protected final JajaCodeNode leftOperand;
    protected final JajaCodeNode rightOperand;

    public JajaCodeOperator(int line, int column, JajaCodeNode lhs, JajaCodeNode rhs) {
        super(line, column);
        this.leftOperand = lhs;
        this.rightOperand = rhs;
    }

    @Override
    public JajaCodeNode leftOperand() {
        return this.leftOperand;
    }

    @Override
    public JajaCodeNode rightOperand() {
        return this.rightOperand;
    }

    @Override
    public JajaCodeNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0 : return this.leftOperand;
            case 1 : return this.rightOperand;
            default: throw new IndexOutOfBoundsException();
        }
    }
}
