package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;

public interface ListExpNode extends ASTNode {
    ASTNode expression();
    ListExpNode listexp();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    public class Builder extends ASTNode.NodeBuilder {
        private ASTNode expression;
        public Builder expression(ASTNode expression) {
            this.expression = expression;
            return this;
        }

        private ListExpNode listexp;
        public Builder listexp(ListExpNode listexp) {
            this.listexp = listexp;
            return this;
        }

        public ListExpNode build() {
            return new ListExpNodeImpl(this.line, this.column, this.expression, this.listexp);
        }
    }
}
