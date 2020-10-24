package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;

public interface HeaderNode extends MiniJajaNode {
    TypeNode type();
    IdentNode identifier();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    public class Builder extends MiniJajaNode.NodeBuilder {
        private TypeNode type;
        public Builder type(TypeNode type){
            this.type = type;
            return this;
        }

        private IdentNode identifier;
        public Builder identifier(IdentNode identifier) {
            this.identifier = identifier;
            return this;
        }

        public HeaderNode build() {
            return new HeaderNodeImpl(this.line, this.column, this.breakpoint, this.type, this.identifier);
        }
    }
}
