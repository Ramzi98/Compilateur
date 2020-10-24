package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;

public interface AndNode extends MiniJajaNode {
    ASTNode leftOperand();
    ASTNode rightOperand();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    public class Builder extends MiniJajaNode.NodeBuilder {
        private ASTNode leftOperand;
        public Builder leftOperand(ASTNode leftOperand) {
            this.leftOperand = leftOperand;
            return this;
        }

        private ASTNode rightOperand;
        public Builder rightOperand(ASTNode rightOperand) {
            this.rightOperand = rightOperand;
            return this;
        }

        public AndNode build() {
            return new AndNodeImpl(this.line, this.column, this.breakpoint, this.leftOperand, this.rightOperand);
        }
    }
}
