package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;

public class JcSwapImpl extends ASTNodeWithInfo implements JcSwapNode {
    public JcSwapImpl(int line, int column) {
        super(line, column);
    }

    @Override
    public ASTNode children(int n) throws IndexOutOfBoundsException {
        throw new IndexOutOfBoundsException();
    }
}
