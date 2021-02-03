package edu.ubfc.st.vm.project.grp7.mjj.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.mjj.ast.MiniJajaNode;

public interface CstNode extends MiniJajaNode {
    TypeNode type();
    IdentNode identifier();
    MiniJajaNode expression();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    class Builder extends MiniJajaNode.NodeBuilder<Builder> {
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

        private MiniJajaNode expression;
        public Builder expression(MiniJajaNode expression) {
            this.expression = expression;
            return this;
        }

        public CstNode build() {
            return new CstNodeImpl(this.line, this.column, this.breakpoint, this.type, this.identifier, this.expression);
        }
    }
}
