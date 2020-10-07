package edu.ubfc.st.vm.project.grp7.ast.visitor;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;

public interface ASTVisitor {
    void visit(ASTNode node) throws Exception;
}
