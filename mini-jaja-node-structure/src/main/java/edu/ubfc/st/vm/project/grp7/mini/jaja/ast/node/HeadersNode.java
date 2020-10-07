package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;

public interface HeadersNode extends ASTNode {
    HeaderNode header();
    HeadersNode headers();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    class Builder extends ASTNode.NodeBuilder {
        private HeaderNode header;
        public Builder instruction(HeaderNode header) {
            this.header = header;
            return this;
        }

        private HeadersNode headers;
        public Builder instrs(HeadersNode headers){
            this.headers = headers;
            return this;
        }

        public HeadersNode build() {
            return new HeadersNodeImpl(this.line, this.column, this.header, this.headers);
        }
    }
}
