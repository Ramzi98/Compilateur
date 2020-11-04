package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;

public interface JcGotoNode extends ASTNode {
    int adresse();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static JcGotoNode.Builder builder() {
        return new JcGotoNode.Builder();
    }

    public class Builder extends ASTNode.NodeBuilder {
        private int adresse;
        public Builder identifier(int adresse) {
            this.adresse = adresse;
            return this;
        }
        public JcGotoNode build() {
            return new JcGotoImpl(this.line, this.column, this.adresse);
        }
    }
}
