package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;

public interface VarsNode extends ASTNode {
    VarNode var();
    VarsNode vars();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    class Builder extends ASTNode.NodeBuilder {
        private VarNode var;
        public Builder instruction(VarNode var) {
            this.var = var;
            return this;
        }

        private VarsNode vars;
        public Builder instrs(VarsNode vars){
            this.vars = vars;
            return this;
        }

        public VarsNode build() {
            return new VarsNodeImpl(this.line, this.column, this.var, this.vars);
        }
    }

}
