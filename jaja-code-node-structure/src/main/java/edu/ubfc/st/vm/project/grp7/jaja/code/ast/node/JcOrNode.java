package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeOperatorNode;

public interface JcOrNode extends JajaCodeOperatorNode {
    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static JcOrNode.Builder builder() {
        return new JcOrNode.Builder();
    }

    class Builder extends JajaCodeOperatorNode.NodeBuilder<JcOrNode.Builder> {
        public JcOrNode build() {
            return new JcOrImpl(this.line, this.column,this.leftOperand, this.rightOperand);
        }
    }
}
