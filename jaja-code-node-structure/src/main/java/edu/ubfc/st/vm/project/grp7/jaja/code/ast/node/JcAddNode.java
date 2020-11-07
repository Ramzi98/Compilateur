package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeOperatorNode;

public interface JcAddNode extends ASTNode {

    JajaCodeNode next();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static JcAddNode.Builder builder() {
        return new JcAddNode.Builder();
    }

    class Builder extends ASTNode.NodeBuilder<JcAddNode.Builder> {
        JajaCodeNode next;
        public JcAddNode.Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }

        public JcAddNode build() {
            return new JcAddImpl(this.line, this.column,this.next);
        }
    }
}
