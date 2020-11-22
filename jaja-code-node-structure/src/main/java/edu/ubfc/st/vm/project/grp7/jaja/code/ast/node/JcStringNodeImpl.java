package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public class JcStringNodeImpl extends ASTNodeBreakpoint implements JcStringNode{
    private final String value;

    public JcStringNodeImpl(int line, int column, Breakpoint bp, String value) {
        super(line, column, bp);
        this.value = value;
    }

    @Override
    public String value() {
        return this.value;
    }

    @Override
    public JajaCodeNode children(int n) throws IndexOutOfBoundsException {
        return null;
    }
}
