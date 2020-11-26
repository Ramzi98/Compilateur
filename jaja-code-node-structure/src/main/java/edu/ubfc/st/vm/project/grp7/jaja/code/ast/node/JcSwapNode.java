package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public interface JcSwapNode extends JajaCodeNode {
    JajaCodeNode next();
    void setNext(JajaCodeNode next);


    static JcSwapNode.Builder builder() {
        return new JcSwapNode.Builder();
    }

    class Builder extends JajaCodeNode.NodeBuilder<JcSwapNode.Builder> {
        JajaCodeNode next;
        public JcSwapNode.Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }

        public JcSwapNode build() {
            return new JcSwapImpl(this.line, this.column, this.breakpoint,this.next);
        }
    }
}
