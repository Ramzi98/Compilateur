package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public interface JcNegNode extends JajaCodeNode {
    JajaCodeNode expression();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    class Builder extends JajaCodeNode.NodeBuilder<Builder> {
        private JajaCodeNode expression;
        public Builder expression(JajaCodeNode expression) {
            this.expression = expression;
            return this;
        }

        public JcNegNode build() {
            return new JcNegImpl(this.line, this.column,this.expression);
        }
    }
}
