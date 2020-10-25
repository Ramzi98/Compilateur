package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;

public interface ClasseNode extends MiniJajaNode {
    IdentNode identifier();
    DeclsNode decls();
    MainNode methmain();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder(){
        return new Builder();
    }

    class Builder extends MiniJajaNode.NodeBuilder<Builder> {
        private IdentNode identifier;
        public Builder identifier(IdentNode id) {
            this.identifier = id;
            return this;
        }

        private DeclsNode decls;
        public Builder decls(DeclsNode decls) {
            this.decls = decls;
            return this;
        }

        private MainNode methmain;
        public Builder methmain(MainNode main) {
            this.methmain = main;
            return this;
        }

        public ClasseNode build(){
            return new ClasseNodeImpl(this.line, this.column, this.breakpoint, this.identifier, this.decls, this.methmain);
        }
    }
}
