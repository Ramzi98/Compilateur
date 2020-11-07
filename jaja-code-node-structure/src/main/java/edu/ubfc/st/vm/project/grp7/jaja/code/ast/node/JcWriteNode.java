package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public interface JcWriteNode extends ASTNode{
    JajaCodeNode next();

    JajaCodeNode next();

    static JcWriteNode.Builder builder() {
        return new JcWriteNode.Builder();
    }

    class Builder extends ASTNode.NodeBuilder {

        JajaCodeNode next;
        public JcWriteNode.Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }

        public JcWriteNode build() {
            return new JcWriteImpl(this.line, this.column, this.next);
        }
    }
}
