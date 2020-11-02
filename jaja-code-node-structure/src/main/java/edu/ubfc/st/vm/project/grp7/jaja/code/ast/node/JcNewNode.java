package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;

public interface JcNewNode extends ASTNode {

    static JcNewNode.Builder builder(){
        return new JcNewNode.Builder();
    }

    @Override
    default void accept(ASTVisitor visitor) throws Exception {
        visitor.visit(this);
    }


    public class Builder extends ASTNode.NodeBuilder<Builder> {
        private String identifier;
        private int type;
        private int sorte;
        private int adr;
        private Breakpoint breakpoint;
        public Builder identifier(String identifier) {
            this.identifier = identifier;
            return this;
        }
        public Builder type(int type) {
            this.type = type;
            return this;
        }
        public Builder sorte(int sorte) {
            this.sorte = sorte;
            return this;
        }
        public Builder adr(int adr) {
            this.adr = adr;
            return this;
        }

        public JcNewNode build(){
            return new JcNewNode(this.line, this.column, this.breakpoint, this.identifier, this.type, this.sorte,this.adr);
        }
    }
}
