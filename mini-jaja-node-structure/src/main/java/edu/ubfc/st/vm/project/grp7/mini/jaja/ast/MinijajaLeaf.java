package edu.ubfc.st.vm.project.grp7.mini.jaja.ast;

import edu.ubfc.st.vm.project.grp7.ast.ASTLeaf;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;

public interface MinijajaLeaf<C> extends MiniJajaNode, ASTLeaf<C> {
    public class LeafBuilder<B, C> extends ASTLeaf.LeafBuilder<B, C> {
        // Default Behaviour No Breakpoint
        protected Breakpoint breakpoint = Breakpoint.NONE;
        public B breakpoint(Breakpoint breakpoint) {
            this.breakpoint = breakpoint;
            return (B)this;
        }
    }
}
