package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MinijajaLeaf;

public interface TypeMethNode extends MinijajaLeaf<TypeMethNode.TypeMeth> {
    static Builder builder () {
        return new Builder();
    }

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    public class Builder extends MinijajaLeaf.LeafBuilder<Builder, TypeMeth> {
        public TypeMethNode build() {
            return new TypeMethNodeImpl(this.line, this.column, this.breakpoint, this.value);
        }
    }

    public enum TypeMeth {
        INT, BOOLEAN, VOID
    }
}
