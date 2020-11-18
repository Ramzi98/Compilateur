package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public class JcInvokeImpl extends ASTNodeWithInfo implements JcInvokeNode {

    private final String identifier;
    private final JajaCodeNode next;
    private JajaCodeNode invokenodejump = null;

    public JcInvokeImpl(int line, int column, String identifier, JajaCodeNode next) {
        super(line, column);
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
    public void setInvokeNodeJump(JajaCodeNode node) {
        this.invokenodejump = node;
    }

    @Override
    public JajaCodeNode getInvokeNodeJump() {
        return invokenodejump;
    }

    @Override
    public JajaCodeNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0 : {return this.next; }
            default: {throw new IndexOutOfBoundsException();}
        }
    }
}
