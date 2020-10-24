package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;

public class VarNodeImpl extends ASTNodeBreakpoint implements VarNode {
    private final TypeMethNode typeMeth;
    private final IdentNode identifier;
    private final MiniJajaNode expression;

    public VarNodeImpl(int line, int column, Breakpoint bp, TypeMethNode typeMeth, IdentNode identifier, MiniJajaNode expression) {
        super(line, column, bp);
        this.typeMeth = typeMeth;
        this.identifier = identifier;
        this.expression = expression;
    }

    @Override
    public TypeMethNode typeMeth() {
        return this.typeMeth;
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
            case 0 : return this.typeMeth;
            case 1 : return this.identifier;
            case 2 : return this.expression;
            default: throw new IndexOutOfBoundsException();
        }
    }
}
