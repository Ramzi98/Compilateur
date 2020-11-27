package edu.ubfc.st.vm.project.grp7.mini.jaja.ast;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.IllFormedNodeException;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;

import java.io.IOException;

public abstract class MiniJajaASTVisitor implements ASTVisitor {
    @Override
    public void visit(ASTNode node) throws Exception {
        if (node == null){
            throw new IllFormedNodeException("Null ASTNode cannot be visited !");
        }


        else if (node instanceof ClasseNode) {
            this.visit((ClasseNode)node);
        }else if (node instanceof IdentNode) {
            this.visit((IdentNode)node);
        }else if (node instanceof DeclsNode) {
            this.visit((DeclsNode)node);
        }else if (node instanceof VarsNode) {
            this.visit((VarsNode) node);
        }else if (node instanceof VarNode) {
            this.visit((VarNode)node);
        }else if (node instanceof ArrayNode) {
            this.visit((ArrayNode) node);
        }else if (node instanceof CstNode) {
            this.visit((CstNode) node);
        }else if (node instanceof MethodNode) {
            this.visit((MethodNode) node);
        }else if (node instanceof MainNode) {
            this.visit((MainNode)node);
        }else if (node instanceof HeadersNode) {
            this.visit((HeadersNode) node);
        }else if (node instanceof HeaderNode) {
            this.visit((HeaderNode) node);
        }else if (node instanceof InstrsNode) {
            this.visit((InstrsNode)node);
        }else if (node instanceof AssignNode) {
            this.visit((AssignNode) node);
        }else if (node instanceof SumNode) {
            this.visit((SumNode) node);
        }else if (node instanceof IncrementNode) {
            this.visit((IncrementNode)node);
        }else if (node instanceof AppelINode) {
            this.visit((AppelINode) node);
        }else if (node instanceof ReturnNode) {
            this.visit((ReturnNode) node);
        }else if (node instanceof WriteNode) {
            this.visit((WriteNode) node);
        }else if (node instanceof WriteLnNode) {
            this.visit((WriteLnNode)node);
        }else if (node instanceof StringNode) {
            this.visit((StringNode) node);
        }else if (node instanceof IfNode) {
            this.visit((IfNode)node);
        }else if (node instanceof WhileNode) {
            this.visit((WhileNode) node);
        }else if (node instanceof ListExpNode) {
            this.visit((ListExpNode)node);
        }else if (node instanceof NotNode) {
            this.visit((NotNode)node);
        }else if (node instanceof AndNode) {
            this.visit((AndNode) node);
        }else if (node instanceof OrNode) {
            this.visit((OrNode)node);
        }else if (node instanceof EqualsNode) {
            this.visit((EqualsNode)node);
        }else if (node instanceof GreaterNode) {
            this.visit((GreaterNode) node);
        }else if (node instanceof PlusNode) {
            this.visit((PlusNode) node);
        }else if (node instanceof SubNode) {
            this.visit((SubNode) node);
        }else if (node instanceof MinusNode) {
            this.visit((MinusNode) node);
        }else if (node instanceof MultNode) {
            this.visit((MultNode) node);
        }else if (node instanceof DivNode) {
            this.visit((DivNode)node);
        }else if (node instanceof AppelENode) {
            this.visit((AppelENode) node);
        }else if (node instanceof BooleanNode) {
            this.visit((BooleanNode) node);
        }else if (node instanceof NumberNode) {
            this.visit((NumberNode) node);
        }else if (node instanceof ArrayItemNode) {
            this.visit((ArrayItemNode)node);
        }else if (node instanceof TypeMethNode) {
            this.visit((TypeMethNode) node);
        }else if (node instanceof TypeNode) {
            this.visit((TypeNode)node);
        }


        else {
            throw new IllFormedNodeException(node.line(), node.column(),
                    node.getClass().getName() + " nodes are not part of a Valid MiniJaja AST"
            );
        }
    }

    public abstract void visit(ClasseNode node) throws IllFormedNodeException, IOException;
    public abstract void visit(IdentNode node) throws IllFormedNodeException, IOException;
    public abstract void visit(DeclsNode node) throws IllFormedNodeException, IOException;
    public abstract void visit(VarsNode node) throws IllFormedNodeException, IOException;
    public abstract void visit(VarNode node) throws IllFormedNodeException, IOException;
    public abstract void visit(ArrayNode node) throws IllFormedNodeException, IOException;
    public abstract void visit(CstNode node) throws IllFormedNodeException, IOException;
    public abstract void visit(MethodNode node) throws IllFormedNodeException, IOException;
    public abstract void visit(MainNode node) throws IllFormedNodeException, IOException;
    public abstract void visit(HeadersNode node) throws IllFormedNodeException, IOException;
    public abstract void visit(HeaderNode node) throws IllFormedNodeException, IOException;
    public abstract void visit(InstrsNode node) throws IllFormedNodeException, IOException;
    public abstract void visit(AssignNode node) throws IllFormedNodeException, IOException;
    public abstract void visit(SumNode node) throws IllFormedNodeException, IOException;
    public abstract void visit(IncrementNode node) throws IllFormedNodeException, IOException;
    public abstract void visit(AppelINode node) throws IllFormedNodeException, IOException;
    public abstract void visit(ReturnNode node) throws IllFormedNodeException, IOException;
    public abstract void visit(WriteNode node) throws IllFormedNodeException, IOException;
    public abstract void visit(WriteLnNode node) throws IllFormedNodeException, IOException;
    public abstract void visit(StringNode node) throws IllFormedNodeException, IOException;
    public abstract void visit(IfNode node) throws IllFormedNodeException, IOException;
    public abstract void visit(WhileNode node) throws IllFormedNodeException, IOException;
    public abstract void visit(ListExpNode node) throws IllFormedNodeException, IOException;
    public abstract void visit(NotNode node) throws IllFormedNodeException, IOException;
    public abstract void visit(AndNode node) throws IllFormedNodeException, IOException;
    public abstract void visit(OrNode node) throws IllFormedNodeException, IOException;
    public abstract void visit(EqualsNode node) throws IllFormedNodeException, IOException;
    public abstract void visit(GreaterNode node) throws IllFormedNodeException, IOException;
    public abstract void visit(PlusNode node) throws IllFormedNodeException, IOException;
    public abstract void visit(SubNode node) throws IllFormedNodeException, IOException;
    public abstract void visit(MinusNode node) throws IllFormedNodeException, IOException;
    public abstract void visit(MultNode node) throws IllFormedNodeException, IOException;
    public abstract void visit(DivNode node) throws IllFormedNodeException, IOException;
    public abstract void visit(AppelENode node) throws IllFormedNodeException, IOException;
    public abstract void visit(BooleanNode node) throws IllFormedNodeException, IOException;
    public abstract void visit(NumberNode node) throws IllFormedNodeException, IOException;
    public abstract void visit(ArrayItemNode node) throws IllFormedNodeException, IOException;
    public abstract void visit(TypeMethNode node) throws IllFormedNodeException, IOException;
    public abstract void visit(TypeNode node) throws IllFormedNodeException, IOException;
}
