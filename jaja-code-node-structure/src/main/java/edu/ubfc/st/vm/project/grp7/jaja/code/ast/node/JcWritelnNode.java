package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public interface JcWritelnNode extends ASTNode {
    JajaCodeNode next();

    static JcWritelnNode.Builder builder() {
        return new JcWritelnNode.Builder();
    }

    public class Builder extends ASTNode.NodeBuilder {
        private JajaCodeNode next;
        public JcWritelnNode.Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }

        public JcWritelnNode build() {
            return new JcWritelnImpl(this.line, this.column,this.next);
        }
    }
}
