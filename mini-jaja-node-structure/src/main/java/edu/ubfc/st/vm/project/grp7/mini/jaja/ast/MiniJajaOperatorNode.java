package edu.ubfc.st.vm.project.grp7.mini.jaja.ast;

public interface MiniJajaOperatorNode extends MiniJajaNode {
    MiniJajaNode leftOperand();
    MiniJajaNode rightOperand();

    abstract class NodeBuilder<B extends NodeBuilder> extends MiniJajaNode.NodeBuilder<B> {
        protected MiniJajaNode leftOperand;
        public final B leftOperand(MiniJajaNode lhs) {
            this.leftOperand = lhs;
            return (B)this;
        }

        protected MiniJajaNode rightOperand;
        public final B rightOperand(MiniJajaNode rhs) {
            this.rightOperand = rhs;
            return (B)this;
        }
    }
}
