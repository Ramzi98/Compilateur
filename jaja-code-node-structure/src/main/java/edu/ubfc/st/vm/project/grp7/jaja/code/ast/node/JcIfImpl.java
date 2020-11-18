package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public class JcIfImpl extends ASTNodeWithInfo implements JcIfNode {

    private int adresse;
    private final JajaCodeNode next;
    private JajaCodeNode ifnodejump = null;


    public JcIfImpl(int line, int column,int adresse,JajaCodeNode next) {
        super(line, column);
        this.adresse = adresse;
        this.next = next;
    }

    @Override
    public JajaCodeNode next() {
        return this.next;
    }

    @Override
    public int adresse() {
        return this.adresse;
    }

    @Override
    public void setIfNodeJump(JajaCodeNode node) {
        this.ifnodejump = node;
    }

    @Override
    public JajaCodeNode getIfNodeJump() {
        return ifnodejump;
    }

    @Override
    public JajaCodeNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0 : {return this.next; }
            default: {throw new IndexOutOfBoundsException();}
        }
    }
}
