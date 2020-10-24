package edu.ubfc.st.vm.project.grp7.mini.jaja.ast;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;

public interface MiniJajaNode extends ASTNode {
    Breakpoint breakpoint();

    abstract class NodeBuilder extends ASTNode.NodeBuilder {
        // No breakpoint as default behaviour
        protected Breakpoint breakpoint = Breakpoint.NONE;
        public final NodeBuilder breakpoint(Breakpoint bp){
            this.breakpoint = bp;
            return this;
        }
    }
}
