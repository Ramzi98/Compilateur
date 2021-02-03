package edu.ubfc.st.vm.project.grp7.jjc.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jjc.ast.JajaCodeNode;

public interface JcSupNode extends JajaCodeNode {
    JajaCodeNode next();
    void setNext(JajaCodeNode next);


    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    class Builder extends JajaCodeNode.NodeBuilder<JcSupNode.Builder> {
        private JajaCodeNode next;
        public JcSupNode.Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }

        public JcSupNode build() {
            return new JcSupImpl(this.line, this.column, this.breakpoint, this.next);
        }
    }
}
