package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;

public interface JcNewNode extends ASTNode {
    String identifier();
    int adr();
    Type type();
    Sorte sorte();


    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static JcNewNode.Builder builder(){ return new JcNewNode.Builder(); }

    class Builder extends ASTNode.NodeBuilder<Builder> {

        private String identifier;
        public Builder identifier(String id) {
            this.identifier = id;
            return this;
        }

        int adr;
        public Builder adr(int adr) {
            this.adr = adr;
            return this;
        }

        Type type;
        public Builder type(Type type) {
            this.type = type;
            return this;
        }
        Sorte sorte;
        public Builder sorte(Sorte sorte) {
            this.sorte = sorte;
            return this;
        }

        public JcNewNode build(){
            return new JcNewImpl(this.line, this.column, this.identifier,this.adr,this.type,this.sorte);
        }


    }
    public enum Sorte {
        Cst, Var, Meth;
    }

    public enum Type {
        INT, BOOLEAN, VOID
    }
}
