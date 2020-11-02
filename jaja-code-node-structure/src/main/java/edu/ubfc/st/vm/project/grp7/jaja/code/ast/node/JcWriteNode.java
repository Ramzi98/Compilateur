package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;

public interface JcWriteNode {

    static JcWriteNode.Builder builder() {
        return new JcWriteNode.Builder();
    }

    public class Builder extends ASTNode.NodeBuilder {
        public JcWriteNode build() {
            return new JcWriteImpl(this.line, this.column);
        }
    }
}
