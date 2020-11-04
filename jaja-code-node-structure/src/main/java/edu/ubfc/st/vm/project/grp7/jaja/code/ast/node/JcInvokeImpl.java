package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;

public class JcInvokeImpl extends ASTNodeWithInfo implements JcInvokeNode {

    private final String identifier;

    public JcInvokeImpl(int line, int column, String identifier) {
        super(line, column);
        this.identifier = identifier;
    }

    @Override
    public String identifier() {
        return this.identifier;
    }


    @Override
    public ASTNode children(int n) throws IndexOutOfBoundsException {
        throw new IndexOutOfBoundsException();
    }
}
