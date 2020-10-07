package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;

public class ArrayNodeImpl extends ASTNodeWithInfo implements ArrayNode {
    private final TypeMethNode typeMeth;
    private final IdentNode identifier;
    private final ASTNode expression;

    public ArrayNodeImpl(int line, int column, TypeMethNode typeMeth, IdentNode identifier, ASTNode expression) {
        super(line, column);
        this.typeMeth = typeMeth;
        this.identifier = identifier;
        this.expression = expression;
    }

    @Override
    public TypeMethNode typeMeth() {
        return this.typeMeth;
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
            case 0 : return this.typeMeth;
            case 1 : return this.identifier;
            case 2 : return this.expression;
            default: throw new IndexOutOfBoundsException();
        }
    }
}
