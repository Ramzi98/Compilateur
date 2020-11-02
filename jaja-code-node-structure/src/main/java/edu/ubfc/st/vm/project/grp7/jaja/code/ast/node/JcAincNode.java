package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;

public interface JcAincNode {

    static JcIfNode.Builder builder() {
        return new JcIfNode.Builder();
    }

    public class Builder extends ASTNode.NodeBuilder {
        public JcIfNode build() {
            return new JcIfImpl(this.line, this.column);
        }
    }
}
