package edu.ubfc.st.vm.project.grp7.mjj.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.mjj.ast.MiniJajaLeaf;

public interface TypeNode extends MiniJajaLeaf<TypeNode.Type> {
    static Builder builder () {
        return new Builder();
    }

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    class Builder extends MiniJajaLeaf.LeafBuilder<Builder, Type> {
        public TypeNode build() {
            return new TypeNodeImpl(this.line, this.column, this.breakpoint, this.value);
        }
    }

    enum Type {
        INT, BOOLEAN
    }
}
