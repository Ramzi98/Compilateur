package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public interface JcWriteNode extends JajaCodeNode{

    JajaCodeNode next();
    void setNext(JajaCodeNode next);

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static JcWriteNode.Builder builder() {
        return new JcWriteNode.Builder();
    }

    class Builder extends JajaCodeNode.NodeBuilder<JcWriteNode.Builder> {
        private JajaCodeNode next;
        public JcWriteNode.Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }
        public JcWriteNode build() {
            return new JcWriteImpl(this.line, this.column,this.breakpoint,this.next);
        }
    }
}
