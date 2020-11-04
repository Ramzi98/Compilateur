package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;

public class JcPushImpl extends ASTNodeWithInfo implements JcPushNode {

    private final Valeur valeur;

    public JcPushImpl(int line, int column, Valeur valeur) {
        super(line, column);
        this.valeur = valeur;
    }

    @Override
    public Valeur valeur() {
        return this.valeur;
    }


    @Override
    public ASTNode children(int n) throws IndexOutOfBoundsException {
        throw new IndexOutOfBoundsException();
    }
}
