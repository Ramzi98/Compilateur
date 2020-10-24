package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTLeaf;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MinijajaLeaf;

public interface StringNode extends MinijajaLeaf<String> {
    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    public class Builder extends MinijajaLeaf.LeafBuilder<Builder, String> {
        public StringNode build() {
            return new StringNodeImpl(this.line, this.column, this.breakpoint, this.value);
        }
    }
}
