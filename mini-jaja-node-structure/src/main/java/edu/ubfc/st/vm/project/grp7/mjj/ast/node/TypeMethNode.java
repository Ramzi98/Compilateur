package edu.ubfc.st.vm.project.grp7.mjj.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.mjj.ast.MiniJajaLeaf;

public interface TypeMethNode extends MiniJajaLeaf<TypeMethNode.TypeMeth> {
    static Builder builder () {
        return new Builder();
    }

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    class Builder extends MiniJajaLeaf.LeafBuilder<Builder, TypeMeth> {
        public TypeMethNode build() {
            return new TypeMethNodeImpl(this.line, this.column, this.breakpoint, this.value);
        }
    }

    enum TypeMeth {
        INT, BOOLEAN, VOID;

        public static TypeMeth from(TypeNode.Type type){
            if (type == TypeNode.Type.BOOLEAN){
                return BOOLEAN;
            }else{
                return INT;
            }
        }
    }
}
