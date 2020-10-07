package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;

public interface ArrayNode extends ASTNode {
    TypeMethNode typeMeth();
    IdentNode identifier();
    ASTNode expression();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    public class Builder extends ASTNode.NodeBuilder {
        private TypeMethNode typeMeth;
        public Builder typeMeth(TypeMethNode typeMeth) {
            this.typeMeth = typeMeth;
            return this;
        }

        private IdentNode identifier;
        public Builder identifier(IdentNode identifier) {
            this.identifier = identifier;
            return this;
        }

        private ASTNode expression;
        public Builder expression(ASTNode expression) {
            this.expression = expression;
            return this;
        }

        public ArrayNode build() {
            return new ArrayNodeImpl(this.line, this.column, this.typeMeth, this.identifier, this.expression);
        }
    }
}
