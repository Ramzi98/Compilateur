package edu.ubfc.st.vm.project.grp7.ast;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;

public interface ASTNode {
    int line();
    int column();

    ASTNode children(int n) throws IndexOutOfBoundsException;

    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    abstract class NodeBuilder {
        protected int line;
        public final NodeBuilder line(int line) {
            this.line = line;
            return this;
        }

        protected int column;
        public final NodeBuilder column(int column) {
            this.column = column;
            return this;
        }
    }
}
