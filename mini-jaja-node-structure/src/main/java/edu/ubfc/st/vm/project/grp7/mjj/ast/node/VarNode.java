package edu.ubfc.st.vm.project.grp7.mjj.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.mjj.ast.MiniJajaNode;

public interface VarNode extends MiniJajaNode {
    TypeMethNode typeMeth();
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
        private TypeMethNode typeMeth;
        public Builder typeMeth(TypeMethNode typeMeth) {
            this.typeMeth = typeMeth;
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

        public VarNode build() {
            return new VarNodeImpl(this.line, this.column, this.breakpoint, this.typeMeth, this.identifier, this.expression);
        }
    }
}
