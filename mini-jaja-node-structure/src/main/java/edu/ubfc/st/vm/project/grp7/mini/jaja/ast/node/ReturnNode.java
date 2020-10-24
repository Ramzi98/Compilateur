package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;

public interface ReturnNode extends MiniJajaNode {
    ASTNode ret();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    public class Builder extends MiniJajaNode.NodeBuilder {
        private ASTNode ret;
        public Builder ret(ASTNode ret) {
            this.ret = ret;
            return this;
        }

        public ReturnNode build() {
            return new ReturnNodeImpl(this.line, this.column, this.breakpoint, this.ret);
        }
    }
}
