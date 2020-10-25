package edu.ubfc.st.vm.project.grp7.ast;

public interface ASTLeaf<C> extends ASTNode {
    C value();

    @Override
    default ASTNode children(int n) throws IndexOutOfBoundsException {
        throw new  IndexOutOfBoundsException();
    }

    abstract class LeafBuilder<B extends LeafBuilder, C> extends ASTNode.NodeBuilder<B> {
        protected C value;
        public B value(C value) {
            this.value = value;
            return (B)this;
        }
    }
}
