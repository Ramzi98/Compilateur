package edu.ubfc.st.vm.project.grp7.jjc.ast;

public interface JajaCodeOperatorNode extends JajaCodeNode {

    JajaCodeNode leftOperand();
    JajaCodeNode rightOperand();

    abstract class NodeBuilder<B extends NodeBuilder> extends JajaCodeNode.NodeBuilder<B> {
        protected JajaCodeNode leftOperand;
        public final B leftOperand(JajaCodeNode lhs) {
            this.leftOperand = lhs;
            return (B)this;
        }

        protected JajaCodeNode rightOperand;
        public final B rightOperand(JajaCodeNode rhs) {
            this.rightOperand = rhs;
            return (B)this;
        }
    }
}
