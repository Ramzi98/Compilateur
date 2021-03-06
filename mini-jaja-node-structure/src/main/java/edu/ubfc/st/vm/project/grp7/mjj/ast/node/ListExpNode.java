package edu.ubfc.st.vm.project.grp7.mjj.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.mjj.ast.MiniJajaNode;

public interface ListExpNode extends MiniJajaNode {
    MiniJajaNode expression();
    ListExpNode listexp();

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

        private ListExpNode listexp;
        public Builder listexp(ListExpNode listexp) {
            this.listexp = listexp;
            return this;
        }

        public ListExpNode build() {
            return new ListExpNodeImpl(this.line, this.column, this.breakpoint, this.expression, this.listexp);
        }
    }
}
