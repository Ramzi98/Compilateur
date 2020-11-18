package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public interface JcGotoNode extends JajaCodeNode {
    int adresse();
    JajaCodeNode next();
    void setGotoNodeJump(JajaCodeNode node);
    JajaCodeNode getGotoNodeJump();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    class Builder extends JajaCodeNode.NodeBuilder<JcGotoNode.Builder> {

        private int adresse;
        public Builder adresse(int adresse) {
            this.adresse = adresse;
            return this;
        }

        JajaCodeNode next;
        public JcGotoNode.Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }

        public JcGotoNode build() {
            return new JcGotoImpl(this.line, this.column, this.adresse, this.next);
        }
    }
}
