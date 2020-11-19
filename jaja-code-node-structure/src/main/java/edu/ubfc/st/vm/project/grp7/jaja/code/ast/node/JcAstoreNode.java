package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public interface JcAstoreNode extends JajaCodeNode {
    String identifier();
    JajaCodeNode next();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder(){ return new Builder(); }

    class Builder extends JajaCodeNode.NodeBuilder<JcAstoreNode.Builder> {

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
            return new JcAstoreImpl(this.line, this.column,this.breakpoint, this.identifier, this.next);
        }

    }
}
