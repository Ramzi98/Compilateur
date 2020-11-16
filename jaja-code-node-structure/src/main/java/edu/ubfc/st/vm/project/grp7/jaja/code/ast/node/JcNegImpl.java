package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public class JcNegImpl extends ASTNodeWithInfo implements JcNegNode {
    private final JajaCodeNode next;

    public JcNegImpl(int line, int column,JajaCodeNode next) {
        super(line, column);
        this.next = next;
        jajacodelist.add((JajaCodeNode) this);
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
