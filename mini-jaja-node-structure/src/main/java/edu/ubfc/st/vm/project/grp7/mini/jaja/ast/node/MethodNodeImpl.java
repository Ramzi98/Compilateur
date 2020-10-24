package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;

public class MethodNodeImpl extends ASTNodeBreakpoint implements MethodNode {
    private final TypeMethNode typeMeth;
    private final IdentNode identifier;
    private final HeadersNode headers;
    private final VarsNode vars;
    private final InstrsNode instrs;

    public MethodNodeImpl(int line, int column, Breakpoint bp, TypeMethNode typeMeth, IdentNode identifier,
                          HeadersNode headers, VarsNode vars, InstrsNode instrs) {
        super(line, column, bp);
        this.typeMeth = typeMeth;
        this.identifier = identifier;
        this.headers = headers;
        this.vars = vars;
        this.instrs = instrs;
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
    public HeadersNode headers() {
        return this.headers;
    }

    @Override
    public VarsNode vars() {
        return this.vars;
    }

    @Override
    public InstrsNode instrs() {
        return this.instrs;
    }

    @Override
    public ASTNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0 : return this.typeMeth;
            case 1 : return this.identifier;
            case 2 : return this.headers;
            case 3 : return this.vars;
            case 4 : return this.instrs;
            default: throw new IndexOutOfBoundsException();
        }
    }
}
