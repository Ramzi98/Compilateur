package edu.ubfc.st.vm.project.grp7.mjj.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.mjj.ast.MiniJajaNode;

public interface VarsNode extends MiniJajaNode {
    MiniJajaNode var();
    VarsNode vars();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    class Builder extends MiniJajaNode.NodeBuilder<Builder> {
        private MiniJajaNode var;
        public Builder var(MiniJajaNode var) {
            this.var = var;
            return this;
        }

        private VarsNode vars;
        public Builder vars(VarsNode vars){
            this.vars = vars;
            return this;
        }

        public VarsNode build() {
            return new VarsNodeImpl(this.line, this.column, this.breakpoint, this.var, this.vars);
        }
    }

}
