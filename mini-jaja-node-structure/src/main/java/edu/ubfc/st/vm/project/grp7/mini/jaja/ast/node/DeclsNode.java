package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;

public interface DeclsNode extends ASTNode {
    ASTNode decl();
    DeclsNode decls();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    public class Builder extends ASTNode.NodeBuilder {
        private ASTNode decl;
        public Builder decl(ASTNode decl) {
            this.decl = decl;
            return this;
        }

        private DeclsNode decls;
        public Builder decls(DeclsNode decls){
            this.decls = decls;
            return this;
        }

        public DeclsNode build() {
            return new DeclsNodeImpl(this.line, this.column, this.decl, this.decls);
        }
    }
}
