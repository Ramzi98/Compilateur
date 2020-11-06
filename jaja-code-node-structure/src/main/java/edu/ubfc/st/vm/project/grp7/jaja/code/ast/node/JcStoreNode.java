package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public interface JcStoreNode extends ASTNode {
    String identifier();
    JajaCodeNode next();

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

        JajaCodeNode next;
        public JcStoreNode.Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }

        public JcStoreNode build(){
            return new JcStoreImpl(this.line, this.column, this.identifier, this.next);
        }

    }
}
