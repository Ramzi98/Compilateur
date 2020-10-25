package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;

public interface VarsNode extends MiniJajaNode {
    VarNode var();
    VarsNode vars();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    class Builder extends MiniJajaNode.NodeBuilder<Builder> {
        private VarNode var;
        public Builder var(VarNode var) {
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
