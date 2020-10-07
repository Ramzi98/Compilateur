package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;

public class SumNodeImpl extends ASTNodeWithInfo implements SumNode {
    private final ASTNode identifier;
    private final ASTNode expression;

    public SumNodeImpl(int line, int column, ASTNode identifier, ASTNode expression) {
        super(line, column);
        this.identifier = identifier;
        this.expression = expression;
    }

    @Override
    public ASTNode identifier() {
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
