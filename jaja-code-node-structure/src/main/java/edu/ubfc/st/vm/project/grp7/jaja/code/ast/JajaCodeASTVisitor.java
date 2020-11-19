package edu.ubfc.st.vm.project.grp7.jaja.code.ast;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.IllFormedNodeException;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.node.*;

import java.io.IOException;

public abstract class JajaCodeASTVisitor implements ASTVisitor {

    @Override
    public void visit(ASTNode node) throws Exception {
        if (node == null){
            throw new IllFormedNodeException("Null ASTNode cannot be visited !");
        }
        else if (node instanceof JcAddNode) {
            this.visit((JcAddNode)node);
        }else if (node instanceof JcAincNode) {
            this.visit((JcAincNode)node);
        }else if (node instanceof JcAloadNode) {
            this.visit((JcAloadNode)node);
        }else if (node instanceof JcAndNode) {
            this.visit((JcAndNode)node);
        }else if (node instanceof JcAstoreNode) {
            this.visit((JcAstoreNode)node);
        }else if (node instanceof JcCmpNode) {
            this.visit((JcCmpNode)node);
        }else if (node instanceof JcDivNode) {
            this.visit((JcDivNode)node);
        }else if (node instanceof JcGotoNode) {
            this.visit((JcGotoNode)node);
        }else if (node instanceof JcIfNode) {
            this.visit((JcIfNode)node);
        }else if (node instanceof JcIncNode) {
            this.visit((JcIncNode)node);
        }else if (node instanceof JcInitNode) {
            this.visit((JcInitNode)node);
        }else if (node instanceof JcInvokeNode) {
            this.visit((JcInvokeNode)node);
        }else if (node instanceof JcLoadNode) {
            this.visit((JcLoadNode)node);
        }else if (node instanceof JcMulNode) {
            this.visit((JcMulNode)node);
        }else if (node instanceof JcNegNode) {
            this.visit((JcNegNode)node);
        }else if (node instanceof JcNewarrayNode) {
            this.visit((JcNewarrayNode)node);
        }else if (node instanceof JcNewNode) {
            this.visit((JcNewNode)node);
        }else if (node instanceof JcNopNode) {
            this.visit((JcNopNode)node);
        }else if (node instanceof JcNotNode) {
            this.visit((JcNotNode)node);
        }else if (node instanceof JcOrNode) {
            this.visit((JcOrNode)node);
        }else if (node instanceof JcPopNode) {
            this.visit((JcPopNode)node);
        }else if (node instanceof JcPushNode) {
            this.visit((JcPushNode)node);
        }else if (node instanceof JcReturnNode) {
            this.visit((JcReturnNode)node);
        }else if (node instanceof JcStoreNode) {
            this.visit((JcStopNode)node);
        }else if (node instanceof JcSubNode) {
            this.visit((JcSubNode)node);
        }else if (node instanceof JcSupNode) {
            this.visit((JcSupNode)node);
        }else if (node instanceof JcSwapNode) {
            this.visit((JcSwapNode)node);
        }else if (node instanceof JcWriteNode) {
            this.visit((JcWriteNode)node);
        }else if (node instanceof JcWritelnNode) {
            this.visit((JcWritelnNode)node);
        } else {
            throw new IllFormedNodeException(node.line(), node.column(),
                    node.getClass().getName() + " nodes are not part of a Valid JajaCode AST"
            );
        }
    }

    public abstract void visit(JcInitImpl node) throws IllFormedNodeException, IOException;

}
