package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public interface JcAincNode extends JajaCodeNode {

    String identifier();
    JajaCodeNode next();
    static JcAincNode.Builder builder() {
        return new JcAincNode.Builder();
    }

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }



    public class Builder extends JajaCodeNode.NodeBuilder<JcAincNode.Builder> {
        private String identifier;
        public JcAincNode.Builder identifier(String identifier) {
            this.identifier = identifier;
            return this;
        }
        JajaCodeNode next;
        public JcAincNode.Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }

        public JcAincNode build() {
            return new JcAincImpl(this.line, this.column,this.breakpoint,this.identifier,this.next);
        }
    }
}
