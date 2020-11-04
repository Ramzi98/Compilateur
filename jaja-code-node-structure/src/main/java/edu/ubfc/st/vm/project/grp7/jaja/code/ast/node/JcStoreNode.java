package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;

public interface JcStoreNode extends ASTNode {
    String identifier();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static JcStoreNode.Builder builder(){ return new JcStoreNode.Builder(); }

    class Builder extends ASTNode.NodeBuilder<JcStoreNode.Builder> {

        private String identifier;
        public Builder identifier(String id) {
            this.identifier = id;
            return this;
        }

        public JcStoreNode build(){
            return new JcStoreImpl(this.line, this.column, this.identifier);
        }

    }
}
