package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;

public interface NotNode extends MiniJajaNode {
    MiniJajaNode expression();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    public class Builder extends MiniJajaNode.NodeBuilder {
        private MiniJajaNode expression;
        public Builder expression(MiniJajaNode expression) {
            this.expression = expression;
            return this;
        }

        public NotNode build() {
            return new NotNodeImpl(this.line, this.column, this.breakpoint, this.expression);
        }
    }
}
