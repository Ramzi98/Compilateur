package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;

public interface PlusNode extends ASTNode {
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
        public Builder rightOperand(ASTNode rightOperand) {
            this.rightOperand = rightOperand;
            return this;
        }

        public PlusNode build() {
            return new PlusNodeImpl(this.line, this.column, this.leftOperand, this.rightOperand);
        }
    }
}
