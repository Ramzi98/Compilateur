package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;

public interface JcPopNode extends ASTNode {
    static JcPopNode.Builder builder() {
        return new JcPopNode.Builder();
    }

    public class Builder extends ASTNode.NodeBuilder {
        public JcPopNode build() {
            return new JcPopImpl(this.line, this.column);
        }
    }
}
