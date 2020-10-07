package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;

public class WriteLnNodeImpl extends ASTNodeWithInfo implements WriteLnNode {
    private final ASTNode printable;

    public WriteLnNodeImpl(int line, int column, ASTNode printable) {
        super(line, column);
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
