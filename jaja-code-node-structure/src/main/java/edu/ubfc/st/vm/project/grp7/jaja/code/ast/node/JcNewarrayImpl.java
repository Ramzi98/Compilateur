package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.TypeMethNode;

public class JcNewarrayImpl extends ASTNodeBreakpoint implements JcNewarrayNode {

    private final String identifier;
    private final TypeMethNode.TypeMeth type;
    private JajaCodeNode next;

    public JcNewarrayImpl(int line, int column, Breakpoint bp, String identifier, TypeMethNode.TypeMeth type, JajaCodeNode next) {
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
    public TypeMethNode.TypeMeth type() {
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
