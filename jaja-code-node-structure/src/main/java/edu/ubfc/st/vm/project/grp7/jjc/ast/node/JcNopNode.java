package edu.ubfc.st.vm.project.grp7.jjc.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jjc.ast.JajaCodeNode;

public interface JcNopNode extends JajaCodeNode {

    JajaCodeNode next();
    void setNext(JajaCodeNode next);


    static JcNopNode.Builder builder() {
        return new JcNopNode.Builder();
    }

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    class Builder extends JajaCodeNode.NodeBuilder<JcNopNode.Builder> {

        private JajaCodeNode next;
        public JcNopNode.Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }

        public JcNopNode build() {
            return new JcNopImpl(this.line, this.column, this.breakpoint, this.next);
        }
    }
}
