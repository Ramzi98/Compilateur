package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public interface JcIfNode extends ASTNode{

    JajaCodeNode next();
    static JcIfNode.Builder builder() {
        return new JcIfNode.Builder();
    }

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }


    public class Builder extends ASTNode.NodeBuilder {
        private int adresse;
        public JcIfNode.Builder adresse(int adresse) {
            this.adresse = adresse;
            return this;
        }
        JajaCodeNode next;
        public JcIfNode.Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }
        public JcIfNode build() {
            return new JcIfImpl(this.line, this.column,this.adresse,this.next);
        }
    }
}
