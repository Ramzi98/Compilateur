package edu.ubfc.st.vm.project.grp7.mjj.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mjj.ast.MiniJajaNode;
import edu.ubfc.st.vm.project.grp7.mjj.ast.MiniJajaOperator;

public class MultNodeImpl extends MiniJajaOperator implements MultNode {
    public MultNodeImpl(int line, int column, Breakpoint bp, MiniJajaNode leftOperand, MiniJajaNode rightOperand) {
        super(line, column, bp, leftOperand, rightOperand);
    }
}
