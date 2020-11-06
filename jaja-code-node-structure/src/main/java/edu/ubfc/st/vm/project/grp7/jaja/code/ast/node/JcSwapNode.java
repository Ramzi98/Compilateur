package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public interface JcSwapNode extends ASTNode {
    JajaCodeNode next();

    static JcSwapNode.Builder builder() {
        return new JcSwapNode.Builder();
    }

    public class Builder extends ASTNode.NodeBuilder {
        JajaCodeNode next;
        public JcSwapNode.Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }

        public JcSwapNode build() {
            return new JcSwapImpl(this.line, this.column,this.next);
        }
    }
}
