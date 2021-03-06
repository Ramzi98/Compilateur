package edu.ubfc.st.vm.project.grp7.jjc.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jjc.ast.JajaCodeNode;

public interface JcGotoNode extends JajaCodeNode {
    int adresse();
    JajaCodeNode next();
    void setAdresse(int adresse);
    void setNext(JajaCodeNode next);
    void setGotonode(JajaCodeNode Gotonode);
    JajaCodeNode gotoNode();

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
            return new JcGotoImpl(this.line, this.column,this.breakpoint, this.adresse, this.next);
        }
    }
}
