package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTLeaf;
import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;

public interface TypeMethNode extends ASTLeaf<TypeMethNode.TypeMeth> {
    static Builder builder () {
        return new Builder();
    }

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    public class Builder extends ASTLeaf.LeafBuilder<Builder, TypeMeth> {
        public TypeMethNode build() {
            return new TypeMethNodeImpl(this.line, this.column, this.value);
        }
    }

    public enum TypeMeth {
        INT, BOOLEAN, VOID
    }
}
