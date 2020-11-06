package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public interface JcInitNode extends ASTNode {
    JajaCodeNode next();

    static Builder builder() {
        return new Builder();
    }

    public class Builder extends ASTNode.NodeBuilder {
        JajaCodeNode next;
        public JcInitNode.Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }

        public JcInitNode build() {
            return new JcInitImpl(this.line, this.column,this.next);
        }
    }
}
