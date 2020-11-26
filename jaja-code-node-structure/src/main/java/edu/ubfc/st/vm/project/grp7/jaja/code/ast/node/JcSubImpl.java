package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeOperator;

public class JcSubImpl extends ASTNodeBreakpoint implements JcSubNode {
    private  JajaCodeNode next;

    public JcSubImpl(int line, int column, Breakpoint breakpoint, JajaCodeNode next) {
        super(line, column, breakpoint);
        this.next = next;
    }

    @Override
    public JajaCodeNode next() {
        return this.next;
    }

    @Override
    public void setNext(JajaCodeNode next) {
        this.next = next;
    }

    @Override
    public JajaCodeNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0 : {return this.next; }
            default: {throw new IndexOutOfBoundsException();}
        }
    }
}
