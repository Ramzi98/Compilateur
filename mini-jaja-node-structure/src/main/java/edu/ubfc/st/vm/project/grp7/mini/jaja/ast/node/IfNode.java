package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;

public interface IfNode extends ASTNode {
    ASTNode expression();
    InstrsNode trueInstrs();
    InstrsNode falseInstrs();

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

        private InstrsNode trueInstrs;
        public Builder trueInstrs(InstrsNode trueInstrs) {
            this.trueInstrs = trueInstrs;
            return this;
        }

        private InstrsNode falseInstrs;
        public Builder falseInstrs(InstrsNode falseInstrs) {
            this.falseInstrs = falseInstrs;
            return this;
        }

        public IfNode build() {
            return new IfNodeImpl(this.line, this.column, this.expression, this.trueInstrs, this.falseInstrs);
        }
    }
}
