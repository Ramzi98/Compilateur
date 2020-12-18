package edu.ubfc.st.vm.project.grp7.mjj.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mjj.ast.MiniJajaNode;

public class ReturnNodeImpl extends ASTNodeBreakpoint implements ReturnNode {
    private final MiniJajaNode ret;

    public ReturnNodeImpl(int line, int column, Breakpoint bp, MiniJajaNode ret) {
        super(line, column, bp);
        this.ret = ret;
    }

    @Override
    public MiniJajaNode ret() {
        return this.ret;
    }

    @Override
    public MiniJajaNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0: return this.ret;
            default: throw new IndexOutOfBoundsException();
        }
    }
}
