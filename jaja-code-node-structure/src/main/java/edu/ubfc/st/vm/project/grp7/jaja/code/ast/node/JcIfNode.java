package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public interface JcIfNode extends JajaCodeNode{

    JajaCodeNode next();
    int adr();

    static JcIfNode.Builder builder() {
        return new JcIfNode.Builder();
    }

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }


    public class Builder extends JajaCodeNode.NodeBuilder<JcIfNode.Builder> {
        private int adr;
        public JcIfNode.Builder adr(int adr) {
            this.adr = adr;
            return this;
        }
        JajaCodeNode next;
        public JcIfNode.Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }
        public JcIfNode build() {
            return new JcIfImpl(this.line, this.column,this.adr,this.next);
        }
    }
}
