package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;

public interface ClasseNode extends ASTNode {
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

    public class Builder extends ASTNode.NodeBuilder {
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
            return new ClasseNodeImpl(line, column, identifier, decls, methmain);
        }
    }
}
