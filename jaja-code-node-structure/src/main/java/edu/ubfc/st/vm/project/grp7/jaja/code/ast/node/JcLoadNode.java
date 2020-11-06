package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public interface JcLoadNode extends ASTNode {
    String identifier();
    JajaCodeNode next();

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

        JajaCodeNode next;
        public JcLoadNode.Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }

        public JcLoadNode build(){
            return new JcLoadImpl(this.line, this.column, this.identifier, this.next);
        }

    }
}
