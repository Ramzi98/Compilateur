package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.ASTNodeWithInfo;

public class ReturnNodeImpl extends ASTNodeWithInfo implements ReturnNode {
    private final ASTNode ret;

    public ReturnNodeImpl(int line, int column, ASTNode ret) {
        super(line, column);
        this.ret = ret;
    }

    @Override
    public ASTNode ret() {
        return this.ret;
    }

    @Override
    public ASTNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0: return this.ret;
            default: throw new IndexOutOfBoundsException();
        }
    }
}
