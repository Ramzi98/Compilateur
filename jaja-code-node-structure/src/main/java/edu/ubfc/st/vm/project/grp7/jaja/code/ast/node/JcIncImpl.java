package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;

public class JcIncImpl extends ASTNodeBreakpoint implements JcIncNode {

    String identifier;

    public JcIncImpl(int line, int column, Breakpoint bp, String identifier) {
            super(line, column,bp);
            this.identifier = identifier;
        }

        @Override
        public ASTNode children(int n) throws IndexOutOfBoundsException {
            throw new IndexOutOfBoundsException();
        }
}
