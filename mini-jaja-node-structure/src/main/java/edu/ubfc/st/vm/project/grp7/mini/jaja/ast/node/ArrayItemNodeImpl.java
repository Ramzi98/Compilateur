package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;

public class ArrayItemNodeImpl extends ASTNodeWithInfo implements ArrayItemNode {
    private final IdentNode identifier;
    private final ASTNode expression;

    public ArrayItemNodeImpl(int line, int column, IdentNode identifier, ASTNode expression) {
        super(line, column);
        this.identifier = identifier;
        this.expression = expression;
    }

    @Override
    public IdentNode identifier() {
        return this.identifier;
    }

    @Override
    public ASTNode expression() {
        return this.expression;
    }

    @Override
    public ASTNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0 : return this.identifier;
            case 1 : return this.expression;
            default: throw new IndexOutOfBoundsException();
        }
    }
}
