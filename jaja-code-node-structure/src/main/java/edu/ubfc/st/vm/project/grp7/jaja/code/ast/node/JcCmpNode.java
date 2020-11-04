package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeOperatorNode;

public interface JcCmpNode extends JajaCodeOperatorNode {
    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static JcCmpNode.Builder builder() {
        return new JcCmpNode.Builder();
    }

    class Builder extends JajaCodeOperatorNode.NodeBuilder<JcCmpNode.Builder> {
        public JcCmpNode build() {
            return new JcCmpImpl(this.line, this.column,this.leftOperand, this.rightOperand);
        }
    }
}
