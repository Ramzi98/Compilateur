package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;

public class StringNodeImpl extends ASTNodeBreakpoint implements StringNode {
    private final String value;

    public StringNodeImpl(int line, int column, Breakpoint bp, String value) {
        super(line, column, bp);
        this.value = value;
    }

    @Override
    public String value() {
        return this.value;
    }
}
