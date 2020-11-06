package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeOperatorNode;

public interface JcSubNode extends JajaCodeOperatorNode {
    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static JcSubNode.Builder builder() {
        return new JcSubNode.Builder();
    }

    class Builder extends JajaCodeOperatorNode.NodeBuilder<JcSubNode.Builder> {

        private JajaCodeNode next;
        public JcSubNode.Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }
        public JcSubNode build() {
            return new JcSubImpl(this.line, this.column,this.leftOperand, this.rightOperand,this.next);
        }
    }
}
