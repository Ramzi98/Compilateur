package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public interface JcInvokeNode extends ASTNode {
    String identifier();
    JajaCodeNode next();

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

        JajaCodeNode next;
        public JcInvokeNode.Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }

        public JcInvokeNode build(){
            return new JcInvokeImpl(this.line, this.column, this.identifier, this.next);
        }

    }

}
