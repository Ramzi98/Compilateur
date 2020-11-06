package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;

public class JcNewImpl extends ASTNodeWithInfo implements JcNewNode {

    private final String identifier;
    private final int adr;
    private final Type type;
    private final Sorte sorte;
    private final JajaCodeNode next;

    public JcNewImpl(int line, int column, String identifier, int adr, Type type, Sorte sorte, JajaCodeNode next) {
        super(line, column);
        this.identifier = identifier;
        this.adr = adr;
        this.type = type;
        this.sorte = sorte;
        this.next = next;
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
    public JajaCodeNode next() {
        return this.next;
    }

    @Override
    public JajaCodeNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0 : {return this.next; }
            default: {throw new IndexOutOfBoundsException();}
        }
    }
}
