package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;

public class AppelENodeImpl extends ASTNodeBreakpoint implements AppelENode {
    private final ListExpNode listexp;
    private final IdentNode identifier;

    public AppelENodeImpl(int line, int column, Breakpoint bp, IdentNode identifier, ListExpNode listexp) {
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
