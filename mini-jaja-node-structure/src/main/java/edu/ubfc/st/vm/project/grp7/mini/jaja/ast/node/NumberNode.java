package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MinijajaLeaf;

public interface NumberNode extends MinijajaLeaf<Double> {
    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    public class Builder extends MinijajaLeaf.LeafBuilder<Builder, Double> {
        public NumberNode build() {
            return new NumberNodeImpl(this.line, this.column, this.breakpoint, this.value);
        }
    }
}
