package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;

public class JcIfImpl extends ASTNodeWithInfo implements JcIfNode {

    private int adresse;

    public JcIfImpl(int line, int column,int adresse) {
        super(line, column);
        this.adresse = adresse;
    }

    @Override
    public ASTNode children(int n) throws IndexOutOfBoundsException {
        throw new IndexOutOfBoundsException();
    }
}
