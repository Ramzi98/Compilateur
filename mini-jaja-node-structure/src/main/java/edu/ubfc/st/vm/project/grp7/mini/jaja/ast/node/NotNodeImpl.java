package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;

public class NotNodeImpl extends ASTNodeWithInfo implements NotNode {
    private final ASTNode expression;

    public NotNodeImpl(int line, int column, ASTNode expression) {
        super(line, column);
        this.expression = expression;
    }

    @Override
    public ASTNode expression() {
        return this.expression;
    }

    @Override
    public ASTNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0 : return this.expression;
            default: throw new IndexOutOfBoundsException();
        }
    }
}
