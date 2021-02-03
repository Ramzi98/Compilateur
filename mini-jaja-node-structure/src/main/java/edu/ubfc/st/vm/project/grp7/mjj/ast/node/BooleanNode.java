package edu.ubfc.st.vm.project.grp7.mjj.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.mjj.ast.MiniJajaLeaf;

public interface BooleanNode extends MiniJajaLeaf<Boolean> {
    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    class Builder extends MiniJajaLeaf.LeafBuilder<Builder, Boolean> {
        public BooleanNode build() {
            return new BooleanNodeImpl(this.line, this.column, this.breakpoint, this.value);
        }
    }
}
