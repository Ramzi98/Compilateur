package edu.ubfc.st.vm.project.grp7.jjc.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jjc.ast.JajaCodeNode;

public interface JcReturnNode extends JajaCodeNode {
    JajaCodeNode next();
    void setNext(JajaCodeNode next);

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static JcReturnNode.Builder builder() {
        return new JcReturnNode.Builder();
    }

    class Builder extends JajaCodeNode.NodeBuilder<JcReturnNode.Builder> {

        JajaCodeNode next;
        public JcReturnNode.Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }
        public JcReturnNode build() {
            return new JcReturnImpl(this.line, this.column, this.breakpoint, this.next);
        }
    }
}
