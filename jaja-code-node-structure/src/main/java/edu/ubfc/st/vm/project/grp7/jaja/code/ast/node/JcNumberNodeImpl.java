package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public class JcNumberNodeImpl  extends ASTNodeBreakpoint implements JcNumberNode{
    private final double value;

    public JcNumberNodeImpl(int line, int column, Breakpoint bp, double value) {
        super(line, column, bp);
        this.value = value;
    }

    @Override
    public double value() {
        return this.value;
    }

    @Override
    public JajaCodeNode children(int n) throws IndexOutOfBoundsException {
        return null;
    }

    @Override
    public void setNext(JajaCodeNode next) throws IndexOutOfBoundsException {
        return;
    }
}
