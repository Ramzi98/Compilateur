package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;

public interface OrNode extends ASTNode {
    ASTNode leftOperand();
    ASTNode rightOperand();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    public class Builder extends ASTNode.NodeBuilder {
        private ASTNode leftOperand;
        public Builder leftOperand(ASTNode leftOperand) {
            this.leftOperand = leftOperand;
            return this;
        }

        private ASTNode rightOperand;
        Builder rightOperand(ASTNode rightOperand) {
            this.rightOperand = rightOperand;
            return this;
        }

        OrNode build() {
            return new OrNodeImpl(this.line, this.column, this.leftOperand, this.rightOperand);
        }
    }
}
