package edu.ubfc.st.vm.project.grp7.jjc.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jjc.ast.JajaCodeNode;

public interface JcAddNode extends JajaCodeNode {

    JajaCodeNode next();
    void setNext(JajaCodeNode next);

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static JcAddNode.Builder builder() {
        return new JcAddNode.Builder();
    }

    class Builder extends JajaCodeNode.NodeBuilder<JcAddNode.Builder> {
        JajaCodeNode next;
        public JcAddNode.Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }

        public JcAddNode build() {
            return new JcAddImpl(this.line, this.column,this.breakpoint,this.next);
        }
    }
}
