package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public interface JcIfNode extends JajaCodeNode {

    JajaCodeNode next();
    int adresse();
    void setAdresse(int adresse);

    static Builder builder() {
        return new Builder();
    }

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }


    class Builder extends JajaCodeNode.NodeBuilder<JcIfNode.Builder> {
        private int adresse;
        public Builder adresse(int adresse) {
            this.adresse = adresse;
            return this;
        }
        JajaCodeNode next;
        public Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }
        public JcIfNode build() {
            return new JcIfImpl(this.line, this.column,this.breakpoint, this.adresse,this.next);
        }
    }
}
