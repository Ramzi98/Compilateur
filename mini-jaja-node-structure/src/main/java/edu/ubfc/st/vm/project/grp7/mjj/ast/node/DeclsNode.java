package edu.ubfc.st.vm.project.grp7.mjj.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.mjj.ast.MiniJajaNode;

public interface DeclsNode extends MiniJajaNode {
    MiniJajaNode decl();
    DeclsNode decls();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    class Builder extends MiniJajaNode.NodeBuilder<Builder> {
        private MiniJajaNode decl;
        public Builder decl(MiniJajaNode decl) {
            this.decl = decl;
            return this;
        }

        private DeclsNode decls;
        public Builder decls(DeclsNode decls){
            this.decls = decls;
            return this;
        }

        public DeclsNode build() {
            return new DeclsNodeImpl(this.line, this.column, this.breakpoint, this.decl, this.decls);
        }
    }
}
