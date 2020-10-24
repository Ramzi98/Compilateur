package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaOperatorNode;

public interface AndNode extends MiniJajaOperatorNode {
    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    public class Builder extends MiniJajaOperatorNode.NodeBuilder {
        public AndNode build() {
            return new AndNodeImpl(this.line, this.column, this.breakpoint, this.leftOperand, this.rightOperand);
        }
    }
}
