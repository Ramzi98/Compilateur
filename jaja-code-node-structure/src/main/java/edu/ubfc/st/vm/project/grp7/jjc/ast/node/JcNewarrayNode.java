package edu.ubfc.st.vm.project.grp7.jjc.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jjc.ast.JajaCodeNode;
import edu.ubfc.st.vm.project.grp7.mjj.ast.node.TypeMethNode;

public interface JcNewarrayNode extends JajaCodeNode {
    String identifier();
    Type type();
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
            return new JcNewarrayImpl(this.line, this.column, this.breakpoint, this.identifier, this.type, this.next);
        }

    }

    enum Type {
        INT{
            @Override
            public String toString() {
                return "entier";
            }
        },
        BOOLEAN{
            @Override
            public String toString() {
                return "booléen";
            }
        },
        VOID{
            @Override
            public String toString() {
                return "void";
            }
        };

        public static Type of(TypeMethNode.TypeMeth value) {
            switch (value) {
                case INT:
                    return INT;
                case BOOLEAN:
                    return BOOLEAN;
                case VOID:
                    return VOID;
                default:
                    return null;
            }
        }
    }
}
