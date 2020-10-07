package edu.ubfc.st.vm.project.grp7.ast;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;

public interface ASTNode {
    int line();
    int column();

    ASTNode children(int n) throws IndexOutOfBoundsException;

    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    public class NodeBuilder {
        protected int line;
        public NodeBuilder line(int line) {
            this.line = line;
            return this;
        }

        protected int column;
        public NodeBuilder column(int column) {
            this.column = column;
            return this;
        }
    }
}
