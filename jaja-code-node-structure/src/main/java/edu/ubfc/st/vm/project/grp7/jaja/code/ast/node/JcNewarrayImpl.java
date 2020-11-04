package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;

public class JcNewarrayImpl extends ASTNodeWithInfo implements JcNewarrayNode {

    private final String identifier;
    private final Type type;

    public JcNewarrayImpl(int line, int column, String identifier,Type type) {
        super(line, column);
        this.identifier = identifier;
        this.type = type;
    }

    @Override
    public String identifier() {
        return this.identifier;
    }

    @Override
    public Type type() {
        return this.type;
    }

    @Override
    public ASTNode children(int n) throws IndexOutOfBoundsException {
        throw new IndexOutOfBoundsException();
    }
}
