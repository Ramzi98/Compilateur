package edu.ubfc.st.vm.project.grp7.mjj.ast;

import edu.ubfc.st.vm.project.grp7.ast.ASTLeaf;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;

public interface MiniJajaLeaf<C> extends MiniJajaNode, ASTLeaf<C> {
    @Override
    default MiniJajaNode children(int n) throws IndexOutOfBoundsException {
        throw new  IndexOutOfBoundsException();
    }

    abstract class LeafBuilder<B extends LeafBuilder, C> extends ASTLeaf.LeafBuilder<B, C> {
        // Default Behaviour No Breakpoint
        protected Breakpoint breakpoint = Breakpoint.NONE;
        public final B breakpoint(Breakpoint breakpoint) {
            this.breakpoint = breakpoint;
            return (B)this;
        }
    }
}
