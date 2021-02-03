package edu.ubfc.st.vm.project.grp7.mjj.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;

public class NumberNodeImpl extends ASTNodeBreakpoint implements NumberNode {
    private final Integer value;

    public NumberNodeImpl(int line, int column, Breakpoint bp, Integer value) {
        super(line, column, bp);
        this.value = value;
    }

    @Override
    public Integer value() {
        return this.value;
    }
}
