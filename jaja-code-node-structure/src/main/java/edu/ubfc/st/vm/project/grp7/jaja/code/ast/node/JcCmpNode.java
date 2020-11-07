package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeOperatorNode;

public interface JcCmpNode extends JajaCodeOperatorNode {

    JajaCodeNode next();
    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static JcCmpNode.Builder builder() {
        return new JcCmpNode.Builder();
    }

    class Builder extends JajaCodeOperatorNode.NodeBuilder<JcCmpNode.Builder> {

        JajaCodeNode next;
        public JcCmpNode.Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }
        public JcCmpNode build() {
            return new JcCmpImpl(this.line, this.column,this.leftOperand, this.rightOperand, this.next);
        }
    }
}
