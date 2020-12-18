package edu.ubfc.st.vm.project.grp7.mjj.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mjj.ast.MiniJajaNode;

public class IdentNodeImpl extends ASTNodeBreakpoint implements IdentNode {
    private final String identifier;
    public IdentNodeImpl(int line, int column, Breakpoint bp, String identifier) {
        super(line, column, bp);
        this.identifier = identifier;
    }

    @Override
    public String value() {
        return this.identifier;
    }

    @Override
    public MiniJajaNode children(int n) throws IndexOutOfBoundsException {
        throw new IndexOutOfBoundsException();
    }
}
