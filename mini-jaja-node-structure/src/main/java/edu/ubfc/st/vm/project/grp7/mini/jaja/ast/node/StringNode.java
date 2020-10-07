package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTLeaf;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;

public interface StringNode extends ASTLeaf<String> {
    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    public class Builder extends ASTLeaf.LeafBuilder<Builder, String> {
        public StringNode build() {
            return new StringNodeImpl(this.line, this.column, this.value);
        }
    }
}
