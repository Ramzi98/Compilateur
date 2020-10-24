package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;

public class ClasseNodeImpl extends ASTNodeBreakpoint implements ClasseNode {
    private final IdentNode identifier;
    private final DeclsNode decls;
    private final MainNode methmain;

    public ClasseNodeImpl(int line, int column, Breakpoint bp, IdentNode identifier, DeclsNode decls, MainNode methmain) {
        super(line, column, bp);
        this.identifier = identifier;
        this.decls = decls;
        this.methmain = methmain;
    }

    @Override
    public IdentNode identifier() {
        return this.identifier;
    }

    @Override
    public DeclsNode decls() {
        return this.decls;
    }

    @Override
    public MainNode methmain() {
        return this.methmain;
    }


    @Override
    public ASTNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0 : return this.identifier;
            case 1 : return this.decls;
            case 2 : return this.methmain;
            default: throw new IndexOutOfBoundsException();
        }
    }
}
