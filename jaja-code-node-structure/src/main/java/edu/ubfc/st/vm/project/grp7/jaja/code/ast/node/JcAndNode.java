package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeOperatorNode;

public interface JcAndNode extends JajaCodeNode {

    JajaCodeNode next();
    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static JcAndNode.Builder builder() {
        return new JcAndNode.Builder();
    }

    class Builder extends JajaCodeNode.NodeBuilder<JcAndNode.Builder> {

        JajaCodeNode next;
        public JcAndNode.Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }
        public JcAndNode build() {
            return new JcAndImpl(this.line, this.column,this.breakpoint, this.next);
        }
    }
}
