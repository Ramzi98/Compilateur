package edu.ubfc.st.vm.project.grp7.mjj.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.mjj.ast.MiniJajaLeaf;

public interface NumberNode extends MiniJajaLeaf<Integer> {
    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    class Builder extends MiniJajaLeaf.LeafBuilder<Builder, Integer> {
        public NumberNode build() {
            return new NumberNodeImpl(this.line, this.column, this.breakpoint, this.value);
        }
    }
}
