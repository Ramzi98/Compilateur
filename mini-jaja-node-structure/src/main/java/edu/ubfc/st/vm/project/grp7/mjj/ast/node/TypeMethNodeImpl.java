package edu.ubfc.st.vm.project.grp7.mjj.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;

public class TypeMethNodeImpl extends ASTNodeBreakpoint implements TypeMethNode {
    private final TypeMeth type;

    public TypeMethNodeImpl(int line, int column, Breakpoint bp, TypeMeth type) {
        super(line, column, bp);
        this.type = type;
    }

    @Override
    public TypeMeth value() {
        return this.type;
    }
}
