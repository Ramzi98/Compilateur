package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeASTVisitor;

public class JcNewImpl extends ASTNodeWithInfo implements JcNewNode {

    private final String identifier;
    private final int adr;
    private final Type type;
    private final Sorte sorte;

    public JcNewImpl(int line, int column, String identifier,int adr,Type type,Sorte sorte) {
        super(line, column);
        this.identifier = identifier;
        this.adr = adr;
        this.type = type;
        this.sorte = sorte;
    }

    @Override
    public String identifier() {
        return this.identifier;
    }

    @Override
    public int adr() {
        return this.adr;
    }

    @Override
    public Type type() {
        return this.type;
    }

    @Override
    public Sorte sorte() {
        return this.sorte;
    }

    @Override
    public ASTNode children(int n) throws IndexOutOfBoundsException {
        throw new IndexOutOfBoundsException();
    }
}
