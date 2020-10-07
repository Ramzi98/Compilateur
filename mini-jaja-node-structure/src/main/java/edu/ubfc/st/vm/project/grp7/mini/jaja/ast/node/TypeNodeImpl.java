package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;

public class TypeNodeImpl extends ASTNodeWithInfo implements TypeNode {
    private final Type type;

    public TypeNodeImpl(int line, int column, Type type) {
        super(line, column);
        this.type = type;
    }

    @Override
    public Type value() {
        return this.type;
    }
}
