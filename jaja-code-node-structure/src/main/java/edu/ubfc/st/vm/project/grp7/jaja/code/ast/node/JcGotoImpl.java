package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public class JcGotoImpl extends ASTNodeBreakpoint implements JcGotoNode {
    private int adresse;
    private JajaCodeNode next;

    public JcGotoImpl(int line, int column, Breakpoint bp, int adresse, JajaCodeNode next) {
        super(line, column,bp);
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
    public void setAdresse(int adresse) {
        this.adresse = adresse;
    }

    @Override
    public void setNext(JajaCodeNode next) {
        this.next = next;
    }

    @Override
    public JajaCodeNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0 : {return this.next;}
            default: {throw new IndexOutOfBoundsException();}
        }
    }
}
