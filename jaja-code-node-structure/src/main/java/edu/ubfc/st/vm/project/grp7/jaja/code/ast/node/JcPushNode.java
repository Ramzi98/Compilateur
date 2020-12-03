package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public interface JcPushNode extends JajaCodeNode {
    Object valeur();
    JajaCodeNode next();
    void setNext(JajaCodeNode next);
    
    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder(){ return new Builder(); }

    class Builder extends JajaCodeNode.NodeBuilder<JcPushNode.Builder> {
        private Object valeur;
        public Builder valeur(Object valeur) {
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
}