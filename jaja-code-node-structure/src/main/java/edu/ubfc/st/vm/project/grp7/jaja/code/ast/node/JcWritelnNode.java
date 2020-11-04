package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;

public interface JcWritelnNode {
    static JcWritelnNode.Builder builder() {
        return new JcWritelnNode.Builder();
    }

    public class Builder extends ASTNode.NodeBuilder {
        public JcWritelnNode build() {
            return new JcWritelnImpl(this.line, this.column);
        }
    }
}
