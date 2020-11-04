package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;

public interface JcInvokeNode extends ASTNode {
    String identifier();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static JcInvokeNode.Builder builder(){ return new JcInvokeNode.Builder(); }

    class Builder extends ASTNode.NodeBuilder<JcInvokeNode.Builder> {

        private String identifier;
        public Builder identifier(String id) {
            this.identifier = id;
            return this;
        }

        public JcInvokeNode build(){
            return new JcInvokeImpl(this.line, this.column, this.identifier);
        }

    }

}
