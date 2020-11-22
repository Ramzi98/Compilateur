package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public interface JcTypeNode extends JajaCodeNode {
    Type type();
    static JcTypeNode.Builder builder () {
        return new JcTypeNode.Builder();
    }

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    class Builder extends JajaCodeNode.NodeBuilder<JcTypeNode.Builder> {

        private Type type;
        public JcTypeNode.Builder value(Type type) {
            this.type = type;
            return this;
        }

        public JcTypeNode build() {
            return new JcTypeNodeImpl(this.line, this.column, this.breakpoint,this.type);
        }
    }
}

enum Type{
    INT,BOOLEAN,VOID;
}
