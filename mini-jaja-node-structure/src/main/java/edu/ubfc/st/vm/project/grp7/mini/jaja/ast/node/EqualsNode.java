package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaOperatorNode;

public interface EqualsNode extends MiniJajaOperatorNode {
    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    class Builder extends MiniJajaOperatorNode.NodeBuilder<Builder> {
        public EqualsNode build() {
            return new EqualsNodeImpl(this.line, this.column, this.breakpoint, this.leftOperand, this.rightOperand);
        }
    }
}
