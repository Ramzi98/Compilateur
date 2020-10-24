package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;

public class InstrsNodeImpl extends ASTNodeBreakpoint implements InstrsNode {
    private final MiniJajaNode instruction;
    private final InstrsNode instrs;

    public InstrsNodeImpl(int line, int column, Breakpoint bp, MiniJajaNode instruction, InstrsNode instrs) {
        super(line, column, bp);
        this.instruction = instruction;
        this.instrs = instrs;
    }

    @Override
    public MiniJajaNode instruction() {
        return this.instruction;
    }

    @Override
    public InstrsNode instrs() {
        return this.instrs;
    }

    @Override
    public MiniJajaNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0 : return this.instruction;
            case 1 : return this.instrs;
            default: throw new IndexOutOfBoundsException();
        }
    }
}
