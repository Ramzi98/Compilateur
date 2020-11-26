package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.TypeMethNode;

public interface JcNewarrayNode extends JajaCodeNode {
    String identifier();
    TypeMethNode.TypeMeth type();
    JajaCodeNode next();
    void setNext(JajaCodeNode next);


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

        TypeMethNode.TypeMeth type;
        public JcNewarrayNode.Builder type(TypeMethNode.TypeMeth type) {
            this.type = type;
            return this;
        }

        JajaCodeNode next;
        public JcNewarrayNode.Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }

        public JcNewarrayNode build(){
            return new JcNewarrayImpl(this.line, this.column,this.breakpoint, this.identifier,this.type,this.next);
        }

    }

    enum Type {
        INT, BOOLEAN, VOID
    }
}
