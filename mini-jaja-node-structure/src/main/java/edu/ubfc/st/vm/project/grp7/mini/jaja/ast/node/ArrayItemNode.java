package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;

public interface ArrayItemNode extends MiniJajaNode {
    IdentNode identifier();
    ASTNode expression();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    public class Builder extends MiniJajaNode.NodeBuilder {
        private IdentNode identifier;
        public Builder identifier(IdentNode identifier){
            this.identifier = identifier;
            return this;
        }

        private ASTNode expression;
        public Builder expression(ASTNode expression) {
            this.expression = expression;
            return this;
        }

        public ArrayItemNode build() {
            return new ArrayItemNodeImpl(this.line, this.column, this.breakpoint, this.identifier, this.expression);
        }
    }
}
