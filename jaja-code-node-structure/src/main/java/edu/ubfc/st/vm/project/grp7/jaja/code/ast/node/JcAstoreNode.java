package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public interface JcAstoreNode extends ASTNode {
    String identifier();
    JajaCodeNode next();

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

        JajaCodeNode next;
        public JcAstoreNode.Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }

        public JcAstoreNode build(){
            return new JcAstoreImpl(this.line, this.column, this.identifier, this.next);
        }

    }
}
