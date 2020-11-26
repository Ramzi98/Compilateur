package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.TypeMethNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.TypeNode;


public class JcNewImpl extends ASTNodeBreakpoint implements JcNewNode {

    private final String identifier;
    private final int adresse;
    private final Type type;
    private final Sorte sorte;
    private JajaCodeNode next;

    public JcNewImpl(int line, int column, Breakpoint bp, String identifier, int adresse, Type type, Sorte sorte, JajaCodeNode next) {
        super(line, column,bp);
        this.identifier = identifier;
        this.adresse = adresse;
        this.type = type;
        this.sorte = sorte;
        this.next = next;
    }

    @Override
    public String identifier() {
        return this.identifier;
    }

    @Override
    public int adresse() {
        return this.adresse;
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
