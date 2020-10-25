package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaLeaf;

public interface StringNode extends MiniJajaLeaf<String> {
    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    class Builder extends MiniJajaLeaf.LeafBuilder<Builder, String> {
        public StringNode build() {
            return new StringNodeImpl(this.line, this.column, this.breakpoint, this.value);
        }
    }
}
