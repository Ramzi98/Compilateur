package edu.ubfc.st.vm.project.grp7.jjc.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jjc.ast.JajaCodeNode;

public interface JcIfNode extends JajaCodeNode {

    JajaCodeNode next();
    void setNext(JajaCodeNode next);
    void setIfNode(JajaCodeNode Ifnode);
    JajaCodeNode ifNode();

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
