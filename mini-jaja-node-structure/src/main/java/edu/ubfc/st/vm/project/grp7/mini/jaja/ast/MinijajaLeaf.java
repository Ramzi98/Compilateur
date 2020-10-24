package edu.ubfc.st.vm.project.grp7.mini.jaja.ast;

import edu.ubfc.st.vm.project.grp7.ast.ASTLeaf;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;

public interface MinijajaLeaf<C> extends MiniJajaNode, ASTLeaf<C> {
    abstract class LeafBuilder<B, C> extends ASTLeaf.LeafBuilder<B, C> {
        // Default Behaviour No Breakpoint
        protected Breakpoint breakpoint = Breakpoint.NONE;
        public final B breakpoint(Breakpoint breakpoint) {
            this.breakpoint = breakpoint;
            return (B)this;
        }
    }
}
