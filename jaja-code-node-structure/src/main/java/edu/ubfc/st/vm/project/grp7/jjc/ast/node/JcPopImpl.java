package edu.ubfc.st.vm.project.grp7.jjc.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.jjc.ast.JajaCodeNode;

public class JcPopImpl extends ASTNodeBreakpoint implements JcPopNode {
    private JajaCodeNode next;

    public JcPopImpl(int line, int column,Breakpoint breakpoint, JajaCodeNode next) {
        super(line, column, breakpoint);
        this.next = next;
    }

    @Override
    public void setNext(JajaCodeNode next) {
        this.next = next;
    }

    @Override
    public JajaCodeNode next() {
        return this.next;
    }

    @Override
    public JajaCodeNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0 : {return this.next; }
            default: {throw new IndexOutOfBoundsException();}
        }
    }
}
