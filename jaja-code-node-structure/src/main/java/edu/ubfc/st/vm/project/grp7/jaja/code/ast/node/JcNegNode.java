package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public interface JcNegNode extends JajaCodeNode {
    JajaCodeNode next();
    void setNext(JajaCodeNode next);


    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static JcNegNode.Builder builder() {
        return new JcNegNode.Builder();
    }

    class Builder extends JajaCodeNode.NodeBuilder<Builder> {
       private JajaCodeNode next;
        public Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }

        public JcNegNode build() {
            return new JcNegImpl(this.line, this.column,this.breakpoint,this.next);
        }
    }
}
