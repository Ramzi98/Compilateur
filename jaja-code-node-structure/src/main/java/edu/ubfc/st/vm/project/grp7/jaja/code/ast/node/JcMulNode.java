package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeOperatorNode;

public interface JcMulNode extends JajaCodeOperatorNode {
    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static JcSupNode.Builder builder() {
        return new JcSupNode.Builder();
    }

    class Builder extends JajaCodeOperatorNode.NodeBuilder<JcMulNode.Builder> {
        public JcMulNode build() {
            return new JcMulImpl(this.line, this.column,this.leftOperand, this.rightOperand);
        }
    }
}
