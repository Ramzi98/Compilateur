package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;

public class IdentNodeImpl extends ASTNodeWithInfo implements IdentNode {
    private final String identifier;
    public IdentNodeImpl(int line, int column, String identifier) {
        super(line, column);
        this.identifier = identifier;
    }

    @Override
    public String value() {
        return this.identifier;
    }

    @Override
    public ASTNode children(int n) throws IndexOutOfBoundsException {
        throw new IndexOutOfBoundsException();
    }
}
