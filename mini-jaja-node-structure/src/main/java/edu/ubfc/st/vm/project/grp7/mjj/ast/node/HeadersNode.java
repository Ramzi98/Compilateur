package edu.ubfc.st.vm.project.grp7.mjj.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.mjj.ast.MiniJajaNode;

public interface HeadersNode extends MiniJajaNode {
    HeaderNode header();
    HeadersNode headers();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    class Builder extends MiniJajaNode.NodeBuilder<Builder> {
        private HeaderNode header;
        public Builder header(HeaderNode header) {
            this.header = header;
            return this;
        }

        private HeadersNode headers;
        public Builder headers(HeadersNode headers){
            this.headers = headers;
            return this;
        }

        public HeadersNode build() {
            return new HeadersNodeImpl(this.line, this.column, this.breakpoint, this.header, this.headers);
        }
    }
}
