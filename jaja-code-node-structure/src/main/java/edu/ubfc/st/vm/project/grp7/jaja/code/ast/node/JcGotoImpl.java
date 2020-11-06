package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public class JcGotoImpl extends ASTNodeWithInfo implements JcGotoNode {
    private final int adresse;
    private final JajaCodeNode next;

    public JcGotoImpl(int line, int column, int adresse, JajaCodeNode next) {
        super(line, column);
        this.adresse = adresse;
        this.next = next;
    }

    @Override
    public int adresse() {
        return this.adresse;
    }

    @Override
    public JajaCodeNode next() {
        return this.next;
    }

    @Override
    public JajaCodeNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0 : {return this.next;}
            default: {throw new IndexOutOfBoundsException();}
        }
    }
}
