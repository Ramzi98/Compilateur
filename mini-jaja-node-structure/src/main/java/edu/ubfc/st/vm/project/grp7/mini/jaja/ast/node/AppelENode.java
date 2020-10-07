package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;

public interface AppelENode extends ASTNode {
    IdentNode identifier();
    ListExpNode listexp();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder bilder() {
        return new Builder();
    }

    public class Builder extends ASTNode.NodeBuilder {
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

        public AppelENode build() {
            return new AppelENodeImpl(this.line, this.column, this.identifier, this.listexp);
        }
    }
}
