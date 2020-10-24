package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;

public class NumberNodeImpl extends ASTNodeBreakpoint implements NumberNode {
    private final Double value;

    public NumberNodeImpl(int line, int column, Breakpoint bp, Double value) {
        super(line, column, bp);
        this.value = value;
    }

    @Override
    public Double value() {
        return this.value;
    }
}
