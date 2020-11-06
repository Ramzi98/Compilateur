package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public interface JcWriteNode extends ASTNode{

    static JcWriteNode.Builder builder() {
        return new JcWriteNode.Builder();
    }

    public class Builder extends ASTNode.NodeBuilder {
        private JajaCodeNode next;
        public JcWriteNode.Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }
        public JcWriteNode build() {
            return new JcWriteImpl(this.line, this.column,this.next);
        }
    }
}
