package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MinijajaLeaf;

public interface IdentNode extends MinijajaLeaf<String> {
    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    public class Builder extends MinijajaLeaf.LeafBuilder<Builder, String> {
        public IdentNode build() {
            return new IdentNodeImpl(this.line, this.column, this.breakpoint, this.value);
        }
    }
}
