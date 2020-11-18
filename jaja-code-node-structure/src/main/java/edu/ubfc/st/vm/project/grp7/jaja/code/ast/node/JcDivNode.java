package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeOperatorNode;

public interface JcDivNode extends JajaCodeNode {
    JajaCodeNode next();
    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static JcDivNode.Builder builder() {
        return new JcDivNode.Builder();
    }

    class Builder extends JajaCodeNode.NodeBuilder<JcDivNode.Builder> {
        JajaCodeNode next;
        public JcDivNode.Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }

        public JcDivNode build() {
            return new JcDivImpl(this.line, this.column,this.breakpoint,this.next);
        }
    }
}
