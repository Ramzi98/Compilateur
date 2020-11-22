package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public class JcPushImpl extends ASTNodeBreakpoint implements JcPushNode {

    private final JajaCodeNode valeur;
    private final JajaCodeNode next;

    public JcPushImpl(int line, int column, Breakpoint breakpoint, JajaCodeNode valeur, JajaCodeNode next) {
        super(line, column, breakpoint);
        this.valeur = valeur;
        this.next = next;
    }

    @Override
    public JajaCodeNode valeur() {
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
