package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;

public interface JcAstoreNode extends ASTNode {
    String identifier();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static JcAstoreNode.Builder builder(){ return new JcAstoreNode.Builder(); }

    class Builder extends ASTNode.NodeBuilder<JcAstoreNode.Builder> {

        private String identifier;
        public Builder identifier(String id) {
            this.identifier = id;
            return this;
        }

        public JcAstoreNode build(){
            return new JcAstoreImpl(this.line, this.column, this.identifier);
        }

    }
}
