package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeOperator;

public class JcSubImpl extends JajaCodeOperator implements JcSubNode {
    public JcSubImpl(int line, int column, JajaCodeNode leftOperand, JajaCodeNode rightOperand) {
        super(line, column,leftOperand, rightOperand);
    }
}
