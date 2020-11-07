package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public interface JcPopNode extends JajaCodeNode {
    JajaCodeNode next();

    static Builder builder() {
        return new Builder();
    }

    class Builder extends JajaCodeNode.NodeBuilder<JcPopNode.Builder> {

        JajaCodeNode next;
        public JcPopNode.Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }

        public JcPopNode build() {
            return new JcPopImpl(this.line, this.column, this.next);
        }
    }
}
