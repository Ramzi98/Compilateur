package edu.ubfc.st.vm.project.grp7.jjc.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jjc.ast.JajaCodeNode;

public interface JcIncNode extends JajaCodeNode {

    String identifier();
    JajaCodeNode next();
    void setNext(JajaCodeNode next);


    static JcIncNode.Builder builder() {
        return new JcIncNode.Builder();
    }

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    class Builder extends JajaCodeNode.NodeBuilder<JcIncNode.Builder> {

        private String identifier;
        public Builder identifier(String identifier) {
            this.identifier = identifier;
            return this;
        }
        JajaCodeNode next;
        public JcIncNode.Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }
        public JcIncNode build() {
            return new JcIncImpl(this.line, this.column,this.breakpoint, this.identifier,this.next);
        }
    }
}
