package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public class JcStopImpl extends ASTNodeWithInfo implements JcStopNode {
    public JcStopImpl(int line, int column) {
        super(line, column);
    }

    @Override
    public JajaCodeNode children(int n) throws IndexOutOfBoundsException {
        throw new IndexOutOfBoundsException();
    }
}
