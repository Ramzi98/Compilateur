package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;

public class HeaderNodeImpl extends ASTNodeWithInfo implements HeaderNode {
    private final TypeNode type;
    private final IdentNode identifier;

    public HeaderNodeImpl(int line, int column, TypeNode type, IdentNode identifier) {
        super(line, column);
        this.type = type;
        this.identifier = identifier;
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
    public ASTNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0 : return this.type;
            case 1 : return this.identifier;
            default: throw new IndexOutOfBoundsException();
        }
    }
}
