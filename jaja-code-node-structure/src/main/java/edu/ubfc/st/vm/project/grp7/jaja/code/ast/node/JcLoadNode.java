package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;

public interface JcLoadNode extends ASTNode {
    String identifier();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static JcLoadNode.Builder builder(){ return new JcLoadNode.Builder(); }

    class Builder extends ASTNode.NodeBuilder<JcLoadNode.Builder> {

        private String identifier;
        public Builder identifier(String id) {
            this.identifier = id;
            return this;
        }

        public JcLoadNode build(){
            return new JcLoadImpl(this.line, this.column, this.identifier);
        }

    }
}
