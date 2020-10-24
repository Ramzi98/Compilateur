package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaOperatorNode;

public interface OrNode extends MiniJajaOperatorNode {
    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    class Builder extends MiniJajaOperatorNode.NodeBuilder {
        public OrNode build() {
            return new OrNodeImpl(this.line, this.column, this.breakpoint, this.leftOperand, this.rightOperand);
        }
    }
}
