package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public class JcIfImpl extends ASTNodeBreakpoint implements JcIfNode {

    private int adresse;
    private JajaCodeNode next;
    private JajaCodeNode Ifnode;



    public JcIfImpl(int line, int column, Breakpoint bp,int adresse, JajaCodeNode next) {
        super(line, column,bp);
        this.adresse = adresse;
        this.next = next;
    }

    @Override
    public JajaCodeNode next() {
        return this.next;
    }

    @Override
    public void setNext(JajaCodeNode next) {
        this.next = next;
    }

    @Override
    public void setIfNode(JajaCodeNode Ifnode) {
        this.Ifnode = Ifnode;
    }

    @Override
    public JajaCodeNode getIfNode() {
        return Ifnode;
    }


    @Override
    public int adresse() {
        return this.adresse;
    }

    @Override
    public void setAdresse(int adresse) {
        this.adresse = adresse;
    }


    @Override
    public JajaCodeNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0 : {return this.next; }
            default: {throw new IndexOutOfBoundsException();}
        }
    }
}
