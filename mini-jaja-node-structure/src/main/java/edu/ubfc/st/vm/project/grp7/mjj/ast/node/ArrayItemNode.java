package edu.ubfc.st.vm.project.grp7.mjj.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.mjj.ast.MiniJajaNode;

public interface ArrayItemNode extends MiniJajaNode {
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
        private IdentNode identifier;
        public Builder identifier(IdentNode identifier){
            this.identifier = identifier;
            return this;
        }

        private MiniJajaNode expression;
        public Builder expression(MiniJajaNode expression) {
            this.expression = expression;
            return this;
        }

        public ArrayItemNode build() {
            return new ArrayItemNodeImpl(this.line, this.column, this.breakpoint, this.identifier, this.expression);
        }
    }
}
