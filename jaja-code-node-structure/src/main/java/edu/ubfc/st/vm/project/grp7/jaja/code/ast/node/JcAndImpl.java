package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeOperator;

public class JcAndImpl extends JajaCodeOperator implements JcAndNode {

    private final JajaCodeNode next;

    public JcAndImpl(int line, int column, JajaCodeNode leftOperand, JajaCodeNode rightOperand,JajaCodeNode next) {
        super(line, column,leftOperand, rightOperand);
        this.next = next;
    }


    @Override
    public JajaCodeNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0 : {return this.next; }
            default: {throw new IndexOutOfBoundsException();}
        }
    }
}
