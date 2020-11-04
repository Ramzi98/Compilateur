package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeOperator;

public class JcAndImpl extends JajaCodeOperator implements JcAndNode {
    public JcAndImpl(int line, int column, JajaCodeNode leftOperand, JajaCodeNode rightOperand) {
        super(line, column,leftOperand, rightOperand);
    }
}
