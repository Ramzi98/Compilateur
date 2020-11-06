package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public interface JcNewNode extends ASTNode {
    String identifier();
    int adr();
    Type type();
    Sorte sorte();
    JajaCodeNode next();


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

        JajaCodeNode next;
        public Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }

        public JcNewNode build(){
            return new JcNewImpl(this.line, this.column, this.identifier,this.adr,this.type,this.sorte,this.next);
        }


    }
    public enum Sorte {
        Cst, Var, Meth;
    }

    public enum Type {
        INT, BOOLEAN, VOID
    }
}
