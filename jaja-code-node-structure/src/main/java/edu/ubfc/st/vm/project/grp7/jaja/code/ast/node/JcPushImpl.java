package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public class JcPushImpl extends ASTNodeWithInfo implements JcPushNode {

    private final Valeur valeur;
    private final JajaCodeNode next;

    public JcPushImpl(int line, int column, Valeur valeur, JajaCodeNode next) {
        super(line, column);
        this.valeur = valeur;
        this.next = next;
    }

    @Override
    public Valeur valeur() {
        return this.valeur;
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
