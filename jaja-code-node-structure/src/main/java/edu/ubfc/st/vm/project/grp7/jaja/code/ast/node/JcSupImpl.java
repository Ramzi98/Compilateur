package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeOperator;

import static edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode.jajacodelist;

public class JcSupImpl extends ASTNodeWithInfo implements JcSupNode {
    private final JajaCodeNode next;

    public JcSupImpl(int line, int column,JajaCodeNode next) {
        super(line, column);
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
