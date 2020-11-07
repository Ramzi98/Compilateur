package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public interface JcIncNode extends ASTNode{

    JajaCodeNode next();

    static JcIncNode.Builder builder() {
        return new JcIncNode.Builder();
    }

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    public class Builder extends ASTNode.NodeBuilder {

        private String identifier;
        public Builder identifier(String identifier) {
            this.identifier = identifier;
            return this;
        }
        JajaCodeNode next;
        public JcIncNode.Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }
        public JcIncNode build() {
            return new JcIncImpl(this.line, this.column,this.identifier,this.next);
        }
    }
}
