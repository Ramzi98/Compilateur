package edu.ubfc.st.vm.project.grp7.mjj.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.mjj.ast.MiniJajaNode;

public interface InstrsNode extends MiniJajaNode {
    MiniJajaNode instruction();
    InstrsNode instrs();

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    class Builder extends MiniJajaNode.NodeBuilder<Builder> {
        private MiniJajaNode instruction;
        public Builder instruction(MiniJajaNode instruction) {
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
