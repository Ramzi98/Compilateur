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
        }else if (node instanceof JcWritelnNode) {
            this.visit(node);
        } else {
            throw new IllFormedNodeException(node.line(), node.column(),
                    node.getClass().getName() + " nodes are not part of a Valid JajaCode AST"
            );
        }
    }

    public abstract void visit(JcInitImpl node) throws IllFormedNodeException, IOException;
    public abstract void visit(JcAddImpl node) throws IllFormedNodeException, IOException;
    public abstract void visit(JcAincImpl node) throws IllFormedNodeException, IOException;
    public abstract void visit(JcAloadImpl node) throws IllFormedNodeException, IOException;
    public abstract void visit(JcAndImpl node) throws IllFormedNodeException, IOException;
    public abstract void visit(JcAstoreImpl node) throws IllFormedNodeException, IOException;
    public abstract void visit(JcCmpImpl node) throws IllFormedNodeException, IOException;
    public abstract void visit(JcDivImpl node) throws IllFormedNodeException, IOException;
    public abstract void visit(JcGotoImpl node) throws IllFormedNodeException, IOException;
    public abstract void visit(JcIfImpl node) throws IllFormedNodeException, IOException;
    public abstract void visit(JcIncImpl node) throws IllFormedNodeException, IOException;
    public abstract void visit(JcInvokeImpl node) throws IllFormedNodeException, IOException;
    public abstract void visit(JcLoadImpl node) throws IllFormedNodeException, IOException;
    public abstract void visit(JcMulImpl node) throws IllFormedNodeException, IOException;
    public abstract void visit(JcNegImpl node) throws IllFormedNodeException, IOException;
    public abstract void visit(JcNewarrayImpl node) throws IllFormedNodeException, IOException;
    public abstract void visit(JcNewImpl node) throws IllFormedNodeException, IOException;
    public abstract void visit(JcNopImpl node) throws IllFormedNodeException, IOException;
    public abstract void visit(JcNotImpl node) throws IllFormedNodeException, IOException;
    public abstract void visit(JcOrImpl node) throws IllFormedNodeException, IOException;
    public abstract void visit(JcPopImpl node) throws IllFormedNodeException, IOException;
    public abstract void visit(JcPushImpl node) throws IllFormedNodeException, IOException;
    public abstract void visit(JcReturnImpl node) throws IllFormedNodeException, IOException;
    public abstract void visit(JcStopImpl node) throws IllFormedNodeException, IOException;
    public abstract void visit(JcStoreImpl node) throws IllFormedNodeException, IOException;
    public abstract void visit(JcSubImpl node) throws IllFormedNodeException, IOException;
    public abstract void visit(JcSupImpl node) throws IllFormedNodeException, IOException;
    public abstract void visit(JcSwapImpl node) throws IllFormedNodeException, IOException;
    public abstract void visit(JcWriteImpl node) throws IllFormedNodeException, IOException;
    public abstract void visit(JcWritelnImpl node) throws IllFormedNodeException, IOException;

}
