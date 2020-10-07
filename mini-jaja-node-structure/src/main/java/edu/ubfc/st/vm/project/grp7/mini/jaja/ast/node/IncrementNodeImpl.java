package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;

public class IncrementNodeImpl extends ASTNodeWithInfo implements IncrementNode {
    private final ASTNode identifier;

    public IncrementNodeImpl(int line, int column, ASTNode identifier) {
        super(line, column);
        this.identifier = identifier;
    }

    @Override
    public ASTNode identifier() {
        return this.identifier;
    }

    @Override
    public ASTNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0 : return this.identifier;
            default: throw new IndexOutOfBoundsException();
        }
    }
}
