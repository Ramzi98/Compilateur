package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;

public class TypeMethNodeImpl extends ASTNodeWithInfo implements TypeMethNode {
    private final TypeMeth type;

    public TypeMethNodeImpl(int line, int column, TypeMeth type) {
        super(line, column);
        this.type = type;
    }

    @Override
    public TypeMeth value() {
        return this.type;
    }
}
