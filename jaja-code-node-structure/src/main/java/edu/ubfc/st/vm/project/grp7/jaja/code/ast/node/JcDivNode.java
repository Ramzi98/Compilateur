package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeOperatorNode;

public interface JcDivNode extends JajaCodeOperatorNode {
    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static JcDivNode.Builder builder() {
        return new JcDivNode.Builder();
    }

    class Builder extends JajaCodeOperatorNode.NodeBuilder<JcDivNode.Builder> {
        public JcDivNode build() {
            return new JcDivImpl(this.line, this.column,this.leftOperand, this.rightOperand);
        }
    }
}
