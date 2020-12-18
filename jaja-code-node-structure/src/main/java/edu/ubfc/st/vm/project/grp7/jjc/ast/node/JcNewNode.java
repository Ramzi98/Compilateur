package edu.ubfc.st.vm.project.grp7.jjc.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jjc.ast.JajaCodeNode;

public interface JcNewNode extends JajaCodeNode {
    String identifier();
    int depth();
    Type type();
    Sorte sorte();
    JajaCodeNode next();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static JcNewNode.Builder builder(){ return new JcNewNode.Builder(); }

    class Builder extends JajaCodeNode.NodeBuilder<JcNewNode.Builder> {
        private String identifier;
        public Builder identifier(String id) {
            this.identifier = id;
            return this;
        }

        private int depth;
        public Builder depth(int depth) {
            this.depth = depth;
            return this;
        }

        private Type type;
        public Builder type(Type type) {
            this.type = type;
            return this;
        }
        
        private Sorte sorte;
        public Builder sorte(Sorte sorte) {
            this.sorte = sorte;
            return this;
        }

        private JajaCodeNode next;
        public Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }

        public JcNewNode build(){
            return new JcNewImpl(this.line, this.column, this.breakpoint,
                    this.identifier, this.depth, this.type, this.sorte, this.next);
        }
    }

    enum Type{
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
    }

    enum Sorte {
        CST{
            @Override
            public String toString() {
                return "cst";
            }
        },
        VAR{
            @Override
            public String toString() {
                return "var";
            }
        },
        METH{
            @Override
            public String toString() {
                return "meth";
            }
        };
    }
}