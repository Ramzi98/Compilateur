package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;

public interface AppelINode extends MiniJajaNode {
    IdentNode identifier();
    ListExpNode listexp();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    public class Builder extends MiniJajaNode.NodeBuilder {
        private IdentNode identifier;
        public Builder identifier(IdentNode identifier) {
            this.identifier = identifier;
            return this;
        }

        private ListExpNode listexp;
        public Builder listexp(ListExpNode listexp) {
            this.listexp = listexp;
            return this;
        }

        public AppelINode build() {
            return new AppelINodeImpl(this.line, this.column, this.breakpoint, this.identifier, this.listexp);
        }
    }
}
