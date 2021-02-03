package edu.ubfc.st.vm.project.grp7.mjj.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.mjj.ast.MiniJajaNode;

public interface WhileNode extends MiniJajaNode {
    MiniJajaNode expression();
    InstrsNode instrs();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    class Builder extends MiniJajaNode.NodeBuilder<Builder> {
        private MiniJajaNode expression;
        public Builder expression(MiniJajaNode expression) {
            this.expression = expression;
            return this;
        }

        private InstrsNode instrs;
        public Builder instrs(InstrsNode instrs) {
            this.instrs = instrs;
            return this;
        }

        public WhileNode build() {
            return new WhileNodeImpl(this.line, this.column, this.breakpoint, this.expression, this.instrs);
        }
    }
}
