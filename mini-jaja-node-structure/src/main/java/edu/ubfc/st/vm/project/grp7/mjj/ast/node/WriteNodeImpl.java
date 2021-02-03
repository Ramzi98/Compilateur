package edu.ubfc.st.vm.project.grp7.mjj.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mjj.ast.MiniJajaNode;

public class WriteNodeImpl extends ASTNodeBreakpoint implements WriteNode {
    private final MiniJajaNode printable;

    public WriteNodeImpl(int line, int column, Breakpoint bp, MiniJajaNode printable) {
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
