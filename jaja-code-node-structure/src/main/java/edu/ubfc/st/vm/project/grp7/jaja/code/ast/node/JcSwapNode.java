package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;

public interface JcSwapNode extends ASTNode {
    static JcSwapNode.Builder builder() {
        return new JcSwapNode.Builder();
    }

    public class Builder extends ASTNode.NodeBuilder {
        public JcSwapNode build() {
            return new JcSwapImpl(this.line, this.column);
        }
    }
}
