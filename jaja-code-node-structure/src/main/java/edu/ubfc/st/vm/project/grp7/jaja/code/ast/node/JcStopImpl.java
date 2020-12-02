package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public class JcStopImpl extends ASTNodeBreakpoint implements JcStopNode {
    public JcStopImpl(int line, int column, Breakpoint breakpoint) {
        super(line, column, breakpoint);
    }

    @Override
    public JajaCodeNode children(int n) throws IndexOutOfBoundsException {
        throw new IndexOutOfBoundsException();
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
