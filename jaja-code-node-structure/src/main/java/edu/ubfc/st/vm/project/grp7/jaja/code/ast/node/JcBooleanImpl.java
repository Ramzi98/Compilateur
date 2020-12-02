package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public class JcBooleanImpl extends ASTNodeBreakpoint implements JcBooleanNode{
    private Boolean value;

    public JcBooleanImpl(int line, int column, Breakpoint bp, Boolean value) {
        super(line, column, bp);
        this.value = value;
    }

    @Override
    public Boolean value() {
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

    @Override
    public JajaCodeNode next() throws IndexOutOfBoundsException {
        return null;
    }
}
