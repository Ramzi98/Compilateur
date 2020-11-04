package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;

public interface JcPushNode extends ASTNode {
    Valeur valeur();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static JcPushNode.Builder builder(){ return new JcPushNode.Builder(); }

    class Builder extends ASTNode.NodeBuilder<JcPushNode.Builder> {

        private Valeur valeur;
        public Builder valeur(Valeur valeur) {
            this.valeur = valeur;
            return this;
        }

        public JcPushNode build(){
            return new JcPushImpl(this.line, this.column,this.valeur);
        }

    }

    enum Valeur{
        INT, BOOLEAN,STRING;
    }


}
