package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;

public class BooleanNodeImpl extends ASTNodeWithInfo implements BooleanNode {
    private final Boolean value;

    public BooleanNodeImpl(int line, int column, Boolean value) {
        super(line, column);
        this.value = value;
    }

    @Override
    public Boolean value() {
        return this.value;
    }
}
