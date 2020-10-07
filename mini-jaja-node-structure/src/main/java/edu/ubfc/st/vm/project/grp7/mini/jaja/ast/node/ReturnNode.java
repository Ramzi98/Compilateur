package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;

public interface ReturnNode extends ASTNode {
    ASTNode ret();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    public class Builder extends ASTNode.NodeBuilder {
        private ASTNode ret;
        public Builder ret(ASTNode ret) {
            this.ret = ret;
            return this;
        }

        public ReturnNode build() {
            return new ReturnNodeImpl(this.line, this.column, this.ret);
        }
    }
}
