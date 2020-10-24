package edu.ubfc.st.vm.project.grp7.mini.jaja.ast;


public interface MiniJajaOperatorNode extends MiniJajaNode {
    MiniJajaNode leftOperand();
    MiniJajaNode rightOperand();

    abstract class NodeBuilder extends MiniJajaNode.NodeBuilder {
        protected MiniJajaNode leftOperand;
        public final NodeBuilder leftOperand(MiniJajaNode lhs) {
            this.leftOperand = lhs;
            return this;
        }

        protected MiniJajaNode rightOperand;
        public final NodeBuilder rightOperand(MiniJajaNode rhs) {
            this.rightOperand = rhs;
            return this;
        }
    }
}
