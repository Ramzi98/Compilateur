package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeOperatorNode;

public interface JcMulNode extends JajaCodeNode {

    JajaCodeNode next();
    void setNext(JajaCodeNode next);


    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static JcMulNode.Builder builder() {
        return new JcMulNode.Builder();
    }

    class Builder extends JajaCodeNode.NodeBuilder<JcMulNode.Builder> {
        JajaCodeNode next;
        public JcMulNode.Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }
        public JcMulNode build() {
            return new JcMulImpl(this.line, this.column,this.breakpoint,this.next);
        }
    }
}
