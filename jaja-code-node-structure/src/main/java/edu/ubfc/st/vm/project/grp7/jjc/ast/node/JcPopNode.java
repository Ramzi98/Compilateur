package edu.ubfc.st.vm.project.grp7.jjc.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jjc.ast.JajaCodeNode;

public interface JcPopNode extends JajaCodeNode {
    JajaCodeNode next();
    void setNext(JajaCodeNode next);


    static Builder builder() {
        return new Builder();
    }

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    class Builder extends JajaCodeNode.NodeBuilder<JcPopNode.Builder> {

        JajaCodeNode next;
        public JcPopNode.Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }

        public JcPopNode build() {
            return new JcPopImpl(this.line, this.column, this.breakpoint, this.next);
        }
    }
}
