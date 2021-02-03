package edu.ubfc.st.vm.project.grp7.jjc.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jjc.ast.JajaCodeNode;

public interface JcWriteLnNode extends JajaCodeNode {
    JajaCodeNode next();
    void setNext(JajaCodeNode next);

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static JcWriteLnNode.Builder builder() {
        return new JcWriteLnNode.Builder();
    }

    class Builder extends JajaCodeNode.NodeBuilder<JcWriteLnNode.Builder> {
        private JajaCodeNode next;
        public JcWriteLnNode.Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }

        public JcWriteLnNode build() {
            return new JcWriteLnImpl(this.line, this.column, this.breakpoint,this.next);
        }
    }
}
