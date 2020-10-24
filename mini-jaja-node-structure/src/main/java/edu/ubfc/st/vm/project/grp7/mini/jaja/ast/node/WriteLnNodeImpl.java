package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;

public class WriteLnNodeImpl extends ASTNodeBreakpoint implements WriteLnNode {
    private final MiniJajaNode printable;

    public WriteLnNodeImpl(int line, int column, Breakpoint bp, MiniJajaNode printable) {
        super(line, column, bp);
        this.printable = printable;
    }

    @Override
    public MiniJajaNode printable() {
        return this.printable;
    }

    @Override
    public MiniJajaNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0 : return this.printable;
            default: throw new IndexOutOfBoundsException();
        }
    }
}
