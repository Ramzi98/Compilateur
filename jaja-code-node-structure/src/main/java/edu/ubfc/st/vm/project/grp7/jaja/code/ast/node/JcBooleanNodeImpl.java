package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public class JcBooleanNodeImpl  extends ASTNodeBreakpoint implements JcBooleanNode{
    private final Boolean value;

    public JcBooleanNodeImpl(int line, int column, Breakpoint bp, Boolean value) {
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
}
