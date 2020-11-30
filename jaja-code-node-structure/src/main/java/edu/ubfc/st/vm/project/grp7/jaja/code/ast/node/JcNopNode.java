package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public interface JcNopNode extends JajaCodeNode{

    JajaCodeNode next();
    void setNext(JajaCodeNode next);


    static JcNopNode.Builder builder() {
        return new JcNopNode.Builder();
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
