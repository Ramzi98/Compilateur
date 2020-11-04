package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;

public interface JcAincNode extends ASTNode {

    static JcAincNode.Builder builder() {
        return new JcAincNode.Builder();
    }

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    public class Builder extends ASTNode.NodeBuilder {
        private String identifier;
        public JcAincNode.Builder identifier(String identifier) {
            this.identifier = identifier;
            return this;
        }
        public JcAincNode build() {
            return new JcAincImpl(this.line, this.column,this.identifier);
        }
    }
}
