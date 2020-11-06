package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public interface JcPopNode extends ASTNode {
    JajaCodeNode next();

    static JcPopNode.Builder builder() {
        return new JcPopNode.Builder();
    }

    public class Builder extends ASTNode.NodeBuilder {

        JajaCodeNode next;
        public JcPopNode.Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }

        public JcPopNode build() {
            return new JcPopImpl(this.line, this.column, this.next);
        }
    }
}
