package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;

public class TypeNodeImpl extends ASTNodeBreakpoint implements TypeNode {
    private final Type type;

    public TypeNodeImpl(int line, int column, Breakpoint bp, Type type) {
        super(line, column, bp);
        this.type = type;
    }

    @Override
    public Type value() {
        return this.type;
    }
}
