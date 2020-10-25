package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;

public interface MainNode extends MiniJajaNode {
    VarsNode vars();
    InstrsNode instrs();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    class Builder extends MiniJajaNode.NodeBuilder<Builder> {
        private VarsNode vars;
        public Builder vars(VarsNode vars) {
            this.vars = vars;
            return this;
        }

        private InstrsNode instrs;
        public Builder instrs(InstrsNode instrs) {
            this.instrs = instrs;
            return this;
        }

        public MainNode build() {
            return new MainNodeImpl(this.line, this.column, this.breakpoint, this.vars, this.instrs);
        }
    }
}
