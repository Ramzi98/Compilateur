package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public interface JcReturnNode extends ASTNode {
    JajaCodeNode next();
    static JcReturnNode.Builder builder() {
        return new JcReturnNode.Builder();
    }

    class Builder extends ASTNode.NodeBuilder {

        JajaCodeNode next;
        public JcReturnNode.Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }
        public JcReturnNode build() {
            return new JcReturnImpl(this.line, this.column, this.next);
        }
    }
}
