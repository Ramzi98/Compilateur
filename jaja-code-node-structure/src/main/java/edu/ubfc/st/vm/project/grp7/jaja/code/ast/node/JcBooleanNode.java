package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public interface JcBooleanNode extends JajaCodeNode{
    Boolean value();
    static JcBooleanNode.Builder builder () {
        return new JcBooleanNode.Builder();
    }

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    class Builder extends JajaCodeNode.NodeBuilder<JcBooleanNode.Builder> {

        private boolean value;
        public JcBooleanNode.Builder value(Boolean value) {
            this.value = value;
            return this;
        }

        public JcBooleanNode build() {
            return new JcBooleanNodeImpl(this.line, this.column, this.breakpoint,this.value);
        }
    }
}
