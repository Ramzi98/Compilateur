package edu.ubfc.st.vm.project.grp7.mjj.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;

public class BooleanNodeImpl extends ASTNodeBreakpoint implements BooleanNode {
    private final Boolean value;

    public BooleanNodeImpl(int line, int column, Breakpoint bp, Boolean value) {
        super(line, column, bp);
        this.value = value;
    }

    @Override
    public Boolean value() {
        return this.value;
    }
}
