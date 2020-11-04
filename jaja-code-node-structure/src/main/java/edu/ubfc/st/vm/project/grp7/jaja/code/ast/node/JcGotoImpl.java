package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;

public class JcGotoImpl extends ASTNodeWithInfo implements JcGotoNode {
    private final int adresse;

    public JcGotoImpl(int line, int column, int adresse) {
        super(line, column);
        this.adresse = adresse;
    }

    @Override
    public int adresse() {
        return this.adresse;
    }

    @Override
    public ASTNode children(int n) throws IndexOutOfBoundsException {
        throw new IndexOutOfBoundsException();
    }
}
