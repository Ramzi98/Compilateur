package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;

public class StringNodeImpl extends ASTNodeWithInfo implements StringNode {
    private final String value;

    public StringNodeImpl(int line, int column, String value) {
        super(line, column);
        this.value = value;
    }

    @Override
    public String value() {
        return this.value;
    }
}
