package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public interface JcNegNode extends JajaCodeNode {
    JajaCodeNode expression();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static JcNegNode.Builder builder() {
        return new JcNegNode.Builder();
    }

    class Builder extends JajaCodeNode.NodeBuilder<Builder> {
        private JajaCodeNode expression;
        public Builder expression(JajaCodeNode expression) {
            this.expression = expression;
            return this;
        }
       private JajaCodeNode next;
        public Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }

        public JcNegNode build() {
            return new JcNegImpl(this.line, this.column,this.expression,this.next);
        }
    }
}
