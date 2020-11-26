package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public interface JcStoreNode extends JajaCodeNode {
    String identifier();
    JajaCodeNode next();
    void setNext(JajaCodeNode next);


    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder(){ return new Builder(); }

    class Builder extends JajaCodeNode.NodeBuilder<JcStoreNode.Builder> {

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
            return new JcStoreImpl(this.line, this.column, this.breakpoint, this.identifier, this.next);
        }

    }
}
