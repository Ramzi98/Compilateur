package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;

public interface InstrsNode extends MiniJajaNode {
    ASTNode instruction();
    InstrsNode instrs();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    class Builder extends MiniJajaNode.NodeBuilder {
        private ASTNode instruction;
        public Builder instruction(ASTNode instruction) {
            this.instruction = instruction;
            return this;
        }

        private InstrsNode instrs;
        public Builder instrs(InstrsNode instrs){
            this.instrs = instrs;
            return this;
        }

        public InstrsNode build() {
            return new InstrsNodeImpl(this.line, this.column, this.breakpoint, this.instruction, this.instrs);
        }
    }
}
