package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;

public class DeclsNodeImpl extends ASTNodeBreakpoint implements DeclsNode {
    private final MiniJajaNode decl;
    private final DeclsNode decls;

    public DeclsNodeImpl(int line, int column, Breakpoint bp, MiniJajaNode decl, DeclsNode decls) {
        super(line, column, bp);
        this.decl = decl;
        this.decls = decls;
    }

    @Override
    public MiniJajaNode decl() {
        return this.decl;
    }

    @Override
    public DeclsNode decls() {
        return this.decls;
    }

    @Override
    public MiniJajaNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0 : return this.decl;
            case 1 : return this.decls;
            default: throw new IndexOutOfBoundsException();
        }
    }
}
