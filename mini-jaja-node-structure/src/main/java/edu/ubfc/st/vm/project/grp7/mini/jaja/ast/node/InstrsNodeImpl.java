package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;

public class InstrsNodeImpl extends ASTNodeWithInfo implements InstrsNode {
    private final ASTNode instruction;
    private final InstrsNode instrs;

    public InstrsNodeImpl(int line, int column, ASTNode instruction, InstrsNode instrs) {
        super(line, column);
        this.instruction = instruction;
        this.instrs = instrs;
    }

    @Override
    public ASTNode instruction() {
        return this.instruction;
    }

    @Override
    public InstrsNode instrs() {
        return this.instrs;
    }

    @Override
    public ASTNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0 : return this.instruction;
            case 1 : return this.instrs;
            default: throw new IndexOutOfBoundsException();
        }
    }
}
