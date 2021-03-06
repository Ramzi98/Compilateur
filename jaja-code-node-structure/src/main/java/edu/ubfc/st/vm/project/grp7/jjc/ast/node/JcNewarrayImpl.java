package edu.ubfc.st.vm.project.grp7.jjc.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.jjc.ast.JajaCodeNode;

public class JcNewarrayImpl extends ASTNodeBreakpoint implements JcNewarrayNode {

    private final String identifier;
    private final Type type;
    private JajaCodeNode next;

    public JcNewarrayImpl(int line, int column, Breakpoint bp, String identifier, Type type, JajaCodeNode next) {
        super(line, column,bp);
        this.identifier = identifier;
        this.type = type;
        this.next = next;
    }

    @Override
    public String identifier() {
        return this.identifier;
    }

    @Override
    public Type type() {
        return this.type;
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
