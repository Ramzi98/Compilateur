package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;

public class JcAincImpl extends ASTNodeWithInfo implements JcAincNode {

    private String identifier;
    public JcAincImpl(int line, int column,String identifier) {
        super(line, column);
        this.identifier = identifier;
    }

    @Override
    public ASTNode children(int n) throws IndexOutOfBoundsException {
        throw new IndexOutOfBoundsException();
    }
}
