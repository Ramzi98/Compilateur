package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public interface JcNotNode extends JajaCodeNode {
    JajaCodeNode expression();
    JajaCodeNode next();


    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static JcNotNode.Builder builder() {
        return new JcNotNode.Builder();
    }

    class Builder extends JajaCodeNode.NodeBuilder<JcNotNode.Builder> {
        private JajaCodeNode expression;
        public JcNotNode.Builder expression(JajaCodeNode expression) {
            this.expression = expression;
            return this;
        }

        private JajaCodeNode next;
        public JcNotNode.Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }

        public JcNotNode build() {
            return new JcNotImpl(this.line, this.column,this.expression,this.next);
        }
    }
}
