package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public interface JcStringNode extends JajaCodeNode{
    String value();
    static JcStringNode.Builder builder () {
        return new JcStringNode.Builder();
    }

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    class Builder extends JajaCodeNode.NodeBuilder<JcStringNode.Builder> {

        private String value;
        public JcStringNode.Builder value(String value) {
            this.value = value;
            return this;
        }

        public JcStringNode build() {
            return new JcStringNodeImpl(this.line, this.column, this.breakpoint,this.value);
        }
    }
}
