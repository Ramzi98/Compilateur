package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;

public interface WriteLnNode extends MiniJajaNode {
    MiniJajaNode printable();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    public class Builder extends MiniJajaNode.NodeBuilder {
        private MiniJajaNode printable;
        public Builder printable(MiniJajaNode printable) {
            this.printable = printable;
            return this;
        }

        public WriteLnNode build() {
            return new WriteLnNodeImpl(this.line, this.column, this.breakpoint, this.printable);
        }
    }
}
