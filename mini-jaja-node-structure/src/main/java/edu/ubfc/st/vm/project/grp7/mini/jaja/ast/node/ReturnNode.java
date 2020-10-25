package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;

public interface ReturnNode extends MiniJajaNode {
    MiniJajaNode ret();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    class Builder extends MiniJajaNode.NodeBuilder<Builder> {
        private MiniJajaNode ret;
        public Builder ret(MiniJajaNode ret) {
            this.ret = ret;
            return this;
        }

        public ReturnNode build() {
            return new ReturnNodeImpl(this.line, this.column, this.breakpoint, this.ret);
        }
    }
}
