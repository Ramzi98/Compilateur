package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public class JcIncImpl extends ASTNodeWithInfo implements JcIncNode {

    String identifier;
    private final JajaCodeNode next;


    public JcIncImpl(int line, int column, String identifier,JajaCodeNode next) {
            super(line, column);
            this.identifier = identifier;
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
