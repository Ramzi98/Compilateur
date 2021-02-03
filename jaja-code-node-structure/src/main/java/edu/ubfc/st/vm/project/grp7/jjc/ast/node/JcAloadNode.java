package edu.ubfc.st.vm.project.grp7.jjc.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jjc.ast.JajaCodeNode;

public interface JcAloadNode extends JajaCodeNode {
    String identifier();
    JajaCodeNode next();
    void setNext(JajaCodeNode next);


    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder(){ return new Builder(); }

    class Builder extends JajaCodeNode.NodeBuilder<JcAloadNode.Builder> {

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
            return new JcAloadImpl(this.line, this.column,this.breakpoint, this.identifier, this.next);
        }

    }
}
