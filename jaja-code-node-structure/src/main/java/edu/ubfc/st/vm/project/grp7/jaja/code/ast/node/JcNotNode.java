package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public interface JcNotNode extends JajaCodeNode {
    JajaCodeNode next();
    void setNext(JajaCodeNode next);

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static JcNotNode.Builder builder() {
        return new JcNotNode.Builder();
    }

    class Builder extends JajaCodeNode.NodeBuilder<JcNotNode.Builder> {


        private JajaCodeNode next;
        public JcNotNode.Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }

        public JcNotNode build() {
            return new JcNotImpl(this.line, this.column, this.breakpoint, this.next);
        }
    }
}
