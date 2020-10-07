package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;

public interface CstNode extends ASTNode {
    TypeNode type();
    IdentNode identifier();
    ASTNode expression();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    public class Builder extends ASTNode.NodeBuilder {
        private TypeNode type;
        public Builder type(TypeNode type) {
            this.type = type;
            return this;
        }

        private IdentNode identifier;
        public Builder identifier(IdentNode identifier) {
            this.identifier = identifier;
            return this;
        }

        private ASTNode expression;
        public Builder expression(ASTNode expression) {
            this.expression = expression;
            return this;
        }

        public CstNode build() {
            return new CstNodeImpl(this.line, this.column, this.type, this.identifier, this.expression);
        }
    }
}
