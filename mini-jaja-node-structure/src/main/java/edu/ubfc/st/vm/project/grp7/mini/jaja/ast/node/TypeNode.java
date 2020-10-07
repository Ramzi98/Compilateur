package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTLeaf;
import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;

public interface TypeNode extends ASTLeaf<TypeNode.Type> {
    static Builder builder () {
        return new Builder();
    }

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    public class Builder extends ASTLeaf.LeafBuilder<Builder, Type> {
        public TypeNode build() {
            return new TypeNodeImpl(this.line, this.column, this.value);
        }
    }

    public enum Type {
        INT, BOOLEAN, VOID
    }
}
