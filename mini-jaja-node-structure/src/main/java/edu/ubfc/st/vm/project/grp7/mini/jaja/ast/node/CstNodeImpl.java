package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;

public class CstNodeImpl extends ASTNodeBreakpoint implements CstNode {
    private final TypeNode type;
    private final IdentNode identifier;
    private final MiniJajaNode expression;

    public CstNodeImpl(int line, int column, Breakpoint bp, TypeNode type, IdentNode identifier, MiniJajaNode expression) {
        super(line, column, bp);
        this.type = type;
        this.identifier = identifier;
        this.expression = expression;
    }

    @Override
    public TypeNode type() {
        return this.type;
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
            case 0 : return this.type;
            case 1 : return this.identifier;
            case 2 : return this.expression;
            default: throw new IndexOutOfBoundsException();
        }
    }
}
