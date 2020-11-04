package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;

public interface JcReturnNode extends ASTNode {
    static JcReturnNode.Builder builder() {
        return new JcReturnNode.Builder();
    }

    public class Builder extends ASTNode.NodeBuilder {
        public JcReturnNode build() {
            return new JcReturnImpl(this.line, this.column);
        }
    }
}
