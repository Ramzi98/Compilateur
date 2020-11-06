package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeOperatorNode;

public interface JcSupNode extends JajaCodeOperatorNode {
    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    class Builder extends JajaCodeOperatorNode.NodeBuilder<JcSupNode.Builder> {
        private JajaCodeNode next;
        public JcSupNode.Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }

        public JcSupNode build() {
            return new JcSupImpl(this.line, this.column,this.leftOperand,this.rightOperand,this.next);
        }
    }
}
