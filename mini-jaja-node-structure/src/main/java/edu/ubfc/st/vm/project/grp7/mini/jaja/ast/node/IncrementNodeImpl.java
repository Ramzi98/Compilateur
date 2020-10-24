package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;

public class IncrementNodeImpl extends ASTNodeBreakpoint implements IncrementNode {
    private final MiniJajaNode identifier;

    public IncrementNodeImpl(int line, int column, Breakpoint bp, MiniJajaNode identifier) {
        super(line, column, bp);
        this.identifier = identifier;
    }

    @Override
    public MiniJajaNode identifier() {
        return this.identifier;
    }

    @Override
    public MiniJajaNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0 : return this.identifier;
            default: throw new IndexOutOfBoundsException();
        }
    }
}
