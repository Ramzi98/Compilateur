package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public interface JcWriteLnNode extends JajaCodeNode {
    JajaCodeNode next();
    void setNext(JajaCodeNode next);


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
