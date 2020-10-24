package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;

public interface WriteNode extends MiniJajaNode {
    ASTNode printable();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    public class Builder extends MiniJajaNode.NodeBuilder {
        private ASTNode printable;
        public Builder printable(ASTNode printable) {
            this.printable = printable;
            return this;
        }

        public WriteNode build() {
            return new WriteNodeImpl(this.line, this.column, this.breakpoint, this.printable);
        }
    }
}
