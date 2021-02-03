package edu.ubfc.st.vm.project.grp7.jjc.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jjc.ast.JajaCodeNode;

public interface JcCmpNode extends JajaCodeNode {

    JajaCodeNode next();
    void setNext(JajaCodeNode next);

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static JcCmpNode.Builder builder() {
        return new JcCmpNode.Builder();
    }

    class Builder extends JajaCodeNode.NodeBuilder<JcCmpNode.Builder> {

        JajaCodeNode next;
        public JcCmpNode.Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }
        public JcCmpNode build() {
            return new JcCmpImpl(this.line, this.column,this.breakpoint,this.next);
        }
    }
}
