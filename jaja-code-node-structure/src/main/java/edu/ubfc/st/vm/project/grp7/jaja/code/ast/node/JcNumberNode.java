package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public interface JcNumberNode extends JajaCodeNode{
    double value();
    static JcNumberNode.Builder builder () {
        return new JcNumberNode.Builder();
    }

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    class Builder extends JajaCodeNode.NodeBuilder<JcNumberNode.Builder> {

        private double value;
        public JcNumberNode.Builder value(double value) {
            this.value = value;
            return this;
        }

        public JcNumberNode build() {
            return new JcNumberImpl(this.line, this.column, this.breakpoint,this.value);
        }
    }
}
