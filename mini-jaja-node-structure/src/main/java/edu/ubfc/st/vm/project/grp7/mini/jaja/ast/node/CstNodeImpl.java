package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;

public class CstNodeImpl extends ASTNodeWithInfo implements CstNode {
    private final TypeNode type;
    private final IdentNode identifier;
    private final ASTNode expression;

    public CstNodeImpl(int line, int column, TypeNode type, IdentNode identifier, ASTNode expression) {
        super(line, column);
        this.type = type;
        this.identifier = identifier;
        this.expression = expression;
    }

    @Override
    public TypeNode type() {
        return this.type;
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
            case 0 : return this.type;
            case 1 : return this.identifier;
            case 2 : return this.expression;
            default: throw new IndexOutOfBoundsException();
        }
    }
}
