package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;

public interface IncrementNode extends MiniJajaNode {
    ASTNode identifier();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    public class Builder extends MiniJajaNode.NodeBuilder {
        private ASTNode identifier;
        public Builder identifier(ASTNode identifier) {
            this.identifier = identifier;
            return this;
        }

        public IncrementNode build() {
            return new IncrementNodeImpl(this.line, this.column, this.breakpoint, this.identifier);
        }
    }
}
