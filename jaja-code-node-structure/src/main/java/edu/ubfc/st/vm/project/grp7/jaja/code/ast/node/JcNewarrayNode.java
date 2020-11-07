package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public interface JcNewarrayNode extends JajaCodeNode {
    String identifier();
    Type type();
    JajaCodeNode next();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static JcNewarrayNode.Builder builder(){ return new JcNewarrayNode.Builder(); }

    class Builder extends JajaCodeNode.NodeBuilder<JcNewarrayNode.Builder> {

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

        JajaCodeNode next;
        public JcNewarrayNode.Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }

        public JcNewarrayNode build(){
            return new JcNewarrayImpl(this.line, this.column, this.identifier,this.type,this.next);
        }

    }

    enum Type {
        INT, BOOLEAN, VOID
    }
}
