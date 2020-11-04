package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;

public interface JcAloadNode extends ASTNode {
    String identifier();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static JcAloadNode.Builder builder(){ return new JcAloadNode.Builder(); }

    class Builder extends ASTNode.NodeBuilder<JcAloadNode.Builder> {

        private String identifier;
        public Builder identifier(String id) {
            this.identifier = id;
            return this;
        }

        public JcAloadNode build(){
            return new JcAloadImpl(this.line, this.column, this.identifier);
        }

    }
}
