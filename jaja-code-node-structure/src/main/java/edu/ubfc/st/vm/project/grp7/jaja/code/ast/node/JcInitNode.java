package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;

public interface JcInitNode extends ASTNode {
    static Builder builder() {
        return new Builder();
    }

    public class Builder extends ASTNode.NodeBuilder {
        public JcInitNode build() {
            return new JcInitImpl(this.line, this.column);
        }
    }
}
