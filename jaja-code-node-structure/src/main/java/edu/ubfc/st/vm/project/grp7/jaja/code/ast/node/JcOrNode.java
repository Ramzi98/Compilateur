package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeOperatorNode;

public interface JcOrNode extends JajaCodeNode {
    JajaCodeNode next();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static JcOrNode.Builder builder() {
        return new JcOrNode.Builder();
    }

    class Builder extends JajaCodeNode.NodeBuilder<JcOrNode.Builder> {

        private JajaCodeNode next;
        public JcOrNode.Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }
        public JcOrNode build() {
            return new JcOrImpl(this.line, this.column, this.breakpoint, this.next);
        }
    }
}
