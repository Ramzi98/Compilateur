package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaOperator;

public class SubNodeImpl extends MiniJajaOperator implements SubNode {
    public SubNodeImpl(int line, int column, Breakpoint bp, MiniJajaNode leftOperand, MiniJajaNode rightOperand) {
        super(line, column, bp, leftOperand, rightOperand);
    }
}
