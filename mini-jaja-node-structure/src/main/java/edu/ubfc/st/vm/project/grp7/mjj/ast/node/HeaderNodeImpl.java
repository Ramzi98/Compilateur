package edu.ubfc.st.vm.project.grp7.mjj.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mjj.ast.MiniJajaNode;

public class HeaderNodeImpl extends ASTNodeBreakpoint implements HeaderNode {
    private final TypeNode type;
    private final IdentNode identifier;

    public HeaderNodeImpl(int line, int column, Breakpoint bp, TypeNode type, IdentNode identifier) {
        super(line, column, bp);
        this.type = type;
        this.identifier = identifier;
    }

    @Override
    public TypeNode type() {
        return this.type;
    }

    @Override
    public IdentNode identifier() {
        return this.identifier;
    }

    @Override
    public MiniJajaNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0 : return this.type;
            case 1 : return this.identifier;
            default: throw new IndexOutOfBoundsException();
        }
    }
}
