package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public class JcAloadImpl extends ASTNodeBreakpoint implements JcAloadNode {

    private final String identifier;
    private final JajaCodeNode next;

    public JcAloadImpl(int line, int column, Breakpoint bp, String identifier, JajaCodeNode next) {
        super(line, column,bp);
        this.identifier = identifier;
        this.next = next;
    }

    @Override
    public String identifier() {
        return this.identifier;
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
