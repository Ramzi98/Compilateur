package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;

public interface JcNopNode extends ASTNode{

    static JcNopNode.Builder builder() {
        return new JcNopNode.Builder();
    }

    public class Builder extends ASTNode.NodeBuilder {
        public JcStopNode build() {
            return new JcStopImpl(this.line, this.column);
        }
    }
}
