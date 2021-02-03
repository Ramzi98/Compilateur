package edu.ubfc.st.vm.project.grp7.jjc.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jjc.ast.JajaCodeNode;

public interface JcStopNode extends JajaCodeNode {

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    static Builder builder() {
        return new Builder();
    }

    class Builder extends JajaCodeNode.NodeBuilder<JcStopNode.Builder> {
        public JcStopNode build() {
            return new JcStopImpl(this.line, this.column, this.breakpoint);
        }
    }

}
