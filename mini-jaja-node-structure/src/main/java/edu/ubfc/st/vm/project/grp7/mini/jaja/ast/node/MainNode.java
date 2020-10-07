package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;

public interface MainNode extends ASTNode {
    VarsNode vars();
    InstrsNode instrs();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    public class Builder extends ASTNode.NodeBuilder {
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
            return new MainNodeImpl(line, column, vars, instrs);
        }
    }
}
