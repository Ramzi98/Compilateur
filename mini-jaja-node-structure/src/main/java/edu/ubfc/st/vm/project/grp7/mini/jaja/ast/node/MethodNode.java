package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;

public interface MethodNode extends MiniJajaNode {
    TypeMethNode typeMeth();
    IdentNode identifier();
    HeadersNode headers();
    VarsNode vars();
    InstrsNode instrs();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    public class Builder extends MiniJajaNode.NodeBuilder {
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

        private HeadersNode headers;
        public Builder headers(HeadersNode headers) {
            this.headers = headers;
            return this;
        }

        private VarsNode vars;
        public Builder vars(VarsNode vars) {
            this.vars = vars;
            return this;
        }

        private InstrsNode instrs;
        public Builder instrs(InstrsNode instrs) {
            this.instrs = instrs;
            return this;
        }

        public MethodNode build() {
            return new MethodNodeImpl(this.line, this.column, this.breakpoint, this.typeMeth,
                    this.identifier, this.headers, this.vars, this.instrs);
        }
    }
}
