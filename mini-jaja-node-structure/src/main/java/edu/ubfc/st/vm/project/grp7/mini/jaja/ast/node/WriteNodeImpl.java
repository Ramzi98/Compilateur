package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;

public class WriteNodeImpl extends ASTNodeBreakpoint implements WriteNode {
    private final ASTNode printable;

    public WriteNodeImpl(int line, int column, Breakpoint bp, ASTNode printable) {
        super(line, column, bp);
        this.printable = printable;
    }

    @Override
    public ASTNode printable() {
        return this.printable;
    }

    @Override
    public ASTNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0 : return this.printable;
            default: throw new IndexOutOfBoundsException();
        }
    }
}
