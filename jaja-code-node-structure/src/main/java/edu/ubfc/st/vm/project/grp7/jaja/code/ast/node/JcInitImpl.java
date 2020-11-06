package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public class JcInitImpl extends ASTNodeWithInfo implements JcInitNode {
    private final JajaCodeNode next;
    public JcInitImpl(int line, int column,JajaCodeNode next) {
        super(line, column);
        this.next = next;
    }

    @Override
    public JajaCodeNode next() {
        return this.next;
    }

    @Override
    public ASTNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0 : {return this.next; }
            default: {throw new IndexOutOfBoundsException();}
        }
    }
}
