package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public class JcNotImpl extends ASTNodeWithInfo implements JcNotNode {
    private final JajaCodeNode expression;

    public JcNotImpl(int line, int column, JajaCodeNode expression) {
        super(line, column);
        this.expression = expression;
    }

    @Override
    public JajaCodeNode expression() {
        return this.expression;
    }

    @Override
    public JajaCodeNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0 : return this.expression;
            default: throw new IndexOutOfBoundsException();
        }
    }
}
