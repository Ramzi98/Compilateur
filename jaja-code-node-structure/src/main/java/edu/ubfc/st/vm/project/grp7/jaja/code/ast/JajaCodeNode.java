package edu.ubfc.st.vm.project.grp7.jaja.code.ast;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;

import java.util.ArrayList;

public interface JajaCodeNode extends ASTNode {

    @Override
    JajaCodeNode children(int n) throws IndexOutOfBoundsException;

    void setNext(JajaCodeNode next) throws IndexOutOfBoundsException;

    abstract class NodeBuilder<B extends NodeBuilder> extends ASTNode.NodeBuilder<B> {
        // No breakpoint as default behaviour
        protected Breakpoint breakpoint = Breakpoint.NONE;
        public final B breakpoint(Breakpoint bp){
            this.breakpoint = bp;
            return (B)this;
        }

        protected Type type = Type.INT;
        public final B type(Type type){
            this.type = type;
            return (B)this;
        }
    }

    public enum Type {
        INT, BOOLEAN, VOID
    }
}


