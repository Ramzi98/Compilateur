package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public interface JcPushNode extends JajaCodeNode {
    Valeur valeur();
    JajaCodeNode next();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder(){ return new Builder(); }

    class Builder extends JajaCodeNode.NodeBuilder<JcPushNode.Builder> {

        private Valeur valeur;
        public Builder valeur(Valeur valeur) {
            this.valeur = valeur;
            return this;
        }

        JajaCodeNode next;
        public JcPushNode.Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }

        public JcPushNode build(){
            return new JcPushImpl(this.line, this.column, this.breakpoint, this.valeur,this.next);
        }

    }

    enum Valeur{
        INT, BOOLEAN,STRING
    }


}
