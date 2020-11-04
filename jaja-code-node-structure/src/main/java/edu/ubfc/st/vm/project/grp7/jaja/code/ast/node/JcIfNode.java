package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;

public interface JcIfNode extends ASTNode{

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
        public JcIfNode build() {
            return new JcIfImpl(this.line, this.column,this.adresse);
        }
    }
}
