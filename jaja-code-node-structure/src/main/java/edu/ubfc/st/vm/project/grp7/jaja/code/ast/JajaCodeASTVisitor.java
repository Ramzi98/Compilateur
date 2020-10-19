package edu.ubfc.st.vm.project.grp7.jaja.code.ast;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.IllFormedNodeException;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.node.JcInitImpl;

import java.io.IOException;

public abstract class JajaCodeASTVisitor implements ASTVisitor {

    @Override
    public void visit(ASTNode node) throws Exception {
        if (node == null){
            throw new IllFormedNodeException("Null ASTNode cannot be visited !");
        }


        else if (node instanceof JcInitImpl) {
            this.visit((JcInitImpl)node);
        }

        else {
            throw new IllFormedNodeException(node.line(), node.column(),
                    node.getClass().getName() + " nodes are not part of a Valid JajaCode AST"
            );
        }
    }

    public abstract void visit(JcInitImpl node) throws IllFormedNodeException, IOException;

}
