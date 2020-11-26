package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public interface JcInitNode extends JajaCodeNode {
    JajaCodeNode next();
    void setNext(JajaCodeNode next);


    static JcInitNode.Builder builder(){ return new JcInitNode.Builder(); }

    class Builder extends JajaCodeNode.NodeBuilder<JcInitNode.Builder>  {
        JajaCodeNode next;
        public JcInitNode.Builder next(JajaCodeNode next) {
            this.next = next;
            return this;
        }

        public JcInitNode build() {
            return new JcInitImpl(this.line, this.column,this.breakpoint, this.next);
        }
    }
}
