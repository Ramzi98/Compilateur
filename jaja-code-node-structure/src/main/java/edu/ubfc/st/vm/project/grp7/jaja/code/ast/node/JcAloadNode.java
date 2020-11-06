package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public interface JcAloadNode extends ASTNode {
    String identifier();
    JajaCodeNode next();

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

        JajaCodeNode next;
        public JcAloadNode.Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }

        public JcAloadNode build(){
            return new JcAloadImpl(this.line, this.column, this.identifier, this.next);
        }

    }
}
