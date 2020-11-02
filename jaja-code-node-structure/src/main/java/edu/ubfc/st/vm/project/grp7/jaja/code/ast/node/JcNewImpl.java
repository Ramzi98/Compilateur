package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeASTVisitor;

public class JcNewImpl extends ASTNodeBreakpoint implements JcNewNode {
    private final String identifier;
    private final int type;
    private final int sorte;
    private final int adr;

    public JcNewImpl(int line, int column, Breakpoint bp, String identifier, int type , int sorte, int adr) {
        super(line, column, bp);
        this.identifier = identifier;
        this.type = type;
        this.sorte = sorte;
        this.adr = adr;
    }

    @Override
    public String identifier() {
        return this.identifier;
    }
    @Override
    public int type() {
        return this.type;
    }
    @Override
    public int sorte() {
        return this.sorte;
    }

    @Override
    public int sorte() {
        return this.sorte;
    }


    @Override
    public ASTNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0 : return this.identifier;
            case 1 : return this.type;
            case 2 : return this.sorte;
            case 3 : return this.adr;
            default: throw new IndexOutOfBoundsException();
        }
    }
}
