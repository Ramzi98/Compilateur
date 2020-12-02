package edu.ubfc.st.vm.project.grp7.jaja.code.ast;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.IllFormedNodeException;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.node.*;

public abstract class JajaCodeASTVisitor implements ASTVisitor {
    @Override
    public void visit(ASTNode node) throws Exception {
        if (node == null){
            throw new IllFormedNodeException("Null ASTNode cannot be visited !");
        }
        else if (node instanceof JcAddNode) {
            this.visit(node);
        }else if (node instanceof JcAincNode) {
            this.visit(node);
        }else if (node instanceof JcAloadNode) {
            this.visit(node);
        }else if (node instanceof JcAndNode) {
            this.visit(node);
        }else if (node instanceof JcAstoreNode) {
            this.visit(node);
        }else if (node instanceof JcCmpNode) {
            this.visit(node);
        }else if (node instanceof JcDivNode) {
            this.visit(node);
        }else if (node instanceof JcGotoNode) {
            this.visit(node);
        }else if (node instanceof JcIfNode) {
            this.visit(node);
        }else if (node instanceof JcIncNode) {
            this.visit(node);
        }else if (node instanceof JcInitNode) {
            this.visit(node);
        }else if (node instanceof JcInvokeNode) {
            this.visit(node);
        }else if (node instanceof JcLoadNode) {
            this.visit(node);
        }else if (node instanceof JcMulNode) {
            this.visit(node);
        }else if (node instanceof JcNegNode) {
            this.visit(node);
        }else if (node instanceof JcNewarrayNode) {
            this.visit(node);
        }else if (node instanceof JcNewNode) {
            this.visit(node);
        }else if (node instanceof JcNopNode) {
            this.visit(node);
        }else if (node instanceof JcNotNode) {
            this.visit(node);
        }else if (node instanceof JcOrNode) {
            this.visit(node);
        }else if (node instanceof JcPopNode) {
            this.visit(node);
        }else if (node instanceof JcPushNode) {
            this.visit(node);
        }else if (node instanceof JcReturnNode) {
            this.visit(node);
        }else if (node instanceof JcStoreNode) {
            this.visit(node);
        }else if (node instanceof JcSubNode) {
            this.visit(node);
        }else if (node instanceof JcSupNode) {
            this.visit(node);
        }else if (node instanceof JcSwapNode) {
            this.visit(node);
        }else if (node instanceof JcWriteNode) {
            this.visit(node);
        }else if (node instanceof JcWriteLnNode) {
            this.visit(node);
        } else {
            throw new IllFormedNodeException(node.line(), node.column(),
                    node.getClass().getName() + " nodes are not part of a Valid JajaCode AST"
            );
        }
    }

    public abstract void visit(JcInitNode node) throws Exception;
    public abstract void visit(JcAddNode node) throws Exception;
    public abstract void visit(JcAincNode node) throws Exception;
    public abstract void visit(JcAloadNode node) throws Exception;
    public abstract void visit(JcAndNode node) throws Exception;
    public abstract void visit(JcAstoreNode node) throws Exception;
    public abstract void visit(JcCmpNode node) throws Exception;
    public abstract void visit(JcDivNode node) throws Exception;
    public abstract void visit(JcGotoNode node) throws Exception;
    public abstract void visit(JcIfNode node) throws Exception;
    public abstract void visit(JcIncNode node) throws Exception;
    public abstract void visit(JcInvokeNode node) throws Exception;
    public abstract void visit(JcLoadNode node) throws Exception;
    public abstract void visit(JcMulNode node) throws Exception;
    public abstract void visit(JcNegNode node) throws Exception;
    public abstract void visit(JcNewarrayNode node) throws Exception;
    public abstract void visit(JcNewNode node) throws Exception;
    public abstract void visit(JcNopNode node) throws Exception;
    public abstract void visit(JcNotNode node) throws Exception;
    public abstract void visit(JcOrNode node) throws Exception;
    public abstract void visit(JcPopNode node) throws Exception;
    public abstract void visit(JcPushNode node) throws Exception;
    public abstract void visit(JcReturnNode node) throws Exception;
    public abstract void visit(JcStopNode node) throws Exception;
    public abstract void visit(JcStoreNode node) throws Exception;
    public abstract void visit(JcSubNode node) throws Exception;
    public abstract void visit(JcSupNode node) throws Exception;
    public abstract void visit(JcSwapNode node) throws Exception;
    public abstract void visit(JcWriteNode node) throws Exception;
    public abstract void visit(JcWriteLnNode node) throws Exception;

}
