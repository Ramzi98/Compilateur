package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;

public class NumberNodeImpl extends ASTNodeWithInfo implements NumberNode {
    private final Double value;

    public NumberNodeImpl(int line, int column, Double value) {
        super(line, column);
        this.value = value;
    }

    @Override
    public Double value() {
        return this.value;
    }
}
