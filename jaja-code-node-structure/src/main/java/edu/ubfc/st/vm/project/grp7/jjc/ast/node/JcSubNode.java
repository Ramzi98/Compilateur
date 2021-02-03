package edu.ubfc.st.vm.project.grp7.jjc.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jjc.ast.JajaCodeNode;

public interface JcSubNode extends JajaCodeNode {

    JajaCodeNode next();
    void setNext(JajaCodeNode next);

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static JcSubNode.Builder builder() {
        return new JcSubNode.Builder();
    }

    class Builder extends JajaCodeNode.NodeBuilder<JcSubNode.Builder> {

        private JajaCodeNode next;
        public JcSubNode.Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }
        public JcSubNode build() {
            return new JcSubImpl(this.line, this.column, this.breakpoint,this.next);
        }
    }
}
