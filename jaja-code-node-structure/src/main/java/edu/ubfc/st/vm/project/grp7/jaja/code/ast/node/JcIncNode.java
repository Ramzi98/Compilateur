package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;

public interface JcIncNode extends ASTNode{

    static JcIncNode.Builder builder() {
        return new JcIncNode.Builder();
    }

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    public class Builder extends ASTNode.NodeBuilder {

        private String identifier;
        private Breakpoint Breakpoint;
        public Builder identifier(String identifier) {
            this.identifier = identifier;
            return this;
        }
        public JcIncNode build() {
            return new JcIncImpl(this.line, this.column,this.Breakpoint,this.identifier);
        }
    }
}
