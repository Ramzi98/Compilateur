package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;

public interface WriteLnNode extends ASTNode {
    ASTNode printable();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    public class Builder extends ASTNode.NodeBuilder {
        private ASTNode printable;
        public Builder printable(ASTNode printable) {
            this.printable = printable;
            return this;
        }

        public WriteLnNode build() {
            return new WriteLnNodeImpl(this.line, this.column, this.printable);
        }
    }
}
