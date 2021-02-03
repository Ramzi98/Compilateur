package edu.ubfc.st.vm.project.grp7.jjc.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.jjc.ast.JajaCodeNode;

public class JcNewImpl extends ASTNodeBreakpoint implements JcNewNode {
    private final String identifier;
    private final int depth;
    private final Type type;
    private final Sorte sorte;
    private JajaCodeNode next;

    public JcNewImpl(int line, int column, Breakpoint bp,
                     String identifier, int depth, Type type, Sorte sorte, JajaCodeNode next) {
        super(line, column,bp);
        this.identifier = identifier;
        this.depth = depth;
        this.type = type;
        this.sorte = sorte;
        this.next = next;
    }

    @Override
    public String identifier() {
        return this.identifier;
    }

    @Override
    public int depth() {
        return this.depth;
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
    public void setNext(JajaCodeNode next) {
        this.next = next;
    }

    @Override
    public JajaCodeNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0 : {return this.next;}
            default: {throw new IndexOutOfBoundsException();}
        }
    }
}
