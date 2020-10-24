package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MinijajaLeaf;

public interface BooleanNode extends MinijajaLeaf<Boolean> {
    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    public class Builder extends MinijajaLeaf.LeafBuilder<Builder, Boolean> {
        public BooleanNode build() {
            return new BooleanNodeImpl(this.line, this.column, this.breakpoint, this.value);
        }
    }
}
