package edu.ubfc.st.vm.project.grp7.mjj.ast;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;

public interface MiniJajaNode extends ASTNode {
    Breakpoint breakpoint();

    @Override
    MiniJajaNode children(int n) throws IndexOutOfBoundsException;

    abstract class NodeBuilder<B extends NodeBuilder> extends ASTNode.NodeBuilder<B> {
        // No breakpoint as default behaviour
        protected Breakpoint breakpoint = Breakpoint.NONE;
        public final B breakpoint(Breakpoint bp){
            this.breakpoint = bp;
            return (B)this;
        }
    }
}
