package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;

public interface JcNewarrayNode extends ASTNode {
    String identifier();
    Type type();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static JcNewarrayNode.Builder builder(){ return new JcNewarrayNode.Builder(); }

    class Builder extends ASTNode.NodeBuilder<JcNewarrayNode.Builder> {

        private String identifier;
        public JcNewarrayNode.Builder identifier(String id) {
            this.identifier = id;
            return this;
        }

        Type type;
        public JcNewarrayNode.Builder type(Type type) {
            this.type = type;
            return this;
        }

        public JcNewarrayNode build(){
            return new JcNewarrayImpl(this.line, this.column, this.identifier,this.type);
        }

    }

    public enum Type {
        INT, BOOLEAN, VOID
    }
}
