package edu.ubfc.st.vm.project.grp7.mjj.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mjj.ast.MiniJajaNode;

public class ArrayItemNodeImpl extends ASTNodeBreakpoint implements ArrayItemNode {
    private final IdentNode identifier;
    private final MiniJajaNode expression;

    public ArrayItemNodeImpl(int line, int column, Breakpoint bp, IdentNode identifier, MiniJajaNode expression) {
        super(line, column, bp);
        this.identifier = identifier;
        this.expression = expression;
    }

    @Override
    public IdentNode identifier() {
        return this.identifier;
    }

    @Override
    public MiniJajaNode expression() {
        return this.expression;
    }

    @Override
    public MiniJajaNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0 : return this.identifier;
            case 1 : return this.expression;
            default: throw new IndexOutOfBoundsException();
        }
    }
}
