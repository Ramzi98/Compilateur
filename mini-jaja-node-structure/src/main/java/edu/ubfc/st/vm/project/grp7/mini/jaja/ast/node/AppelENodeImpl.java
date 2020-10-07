package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;

public class AppelENodeImpl extends ASTNodeWithInfo implements AppelENode {
    private final ListExpNode listexp;
    private final IdentNode identifier;

    public AppelENodeImpl(int line, int column, IdentNode identifier, ListExpNode listexp) {
        super(line, column);
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
    public ASTNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0 : return this.identifier;
            case 1 : return this.listexp;
            default: throw new IndexOutOfBoundsException();
        }
    }
}
