package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public class JcTypeNodeImpl extends ASTNodeBreakpoint implements JcTypeNode {
    private final JcTypeNode.Type type;

    public JcTypeNodeImpl(int line, int column, Breakpoint bp, JcTypeNode.Type type) {
        super(line, column, bp);
        this.type = type;
    }

    @Override
    public JcTypeNode.Type type() {
        return this.type;
    }

    @Override
    public JajaCodeNode children(int n) throws IndexOutOfBoundsException {
        return null;
    }
}
