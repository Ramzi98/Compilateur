package edu.ubfc.st.vm.project.grp7.mjj.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mjj.ast.MiniJajaNode;

public class AppelINodeImpl extends ASTNodeBreakpoint implements AppelINode {
    private final ListExpNode listexp;
    private final IdentNode identifier;

    public AppelINodeImpl(int line, int column, Breakpoint bp, IdentNode identifier, ListExpNode listexp) {
        super(line, column, bp);
        this.listexp = listexp;
        this.identifier = identifier;
    }

    @Override
    public IdentNode identifier() {
        return this.identifier;
    }

    @Override
    public ListExpNode listexp() {
        return this.listexp;
    }

    @Override
    public MiniJajaNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0 : return this.identifier;
            case 1 : return this.listexp;
            default: throw new IndexOutOfBoundsException();
        }
    }
}
