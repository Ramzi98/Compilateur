package edu.ubfc.st.vm.project.grp7.mini.jaja.interpreter;

import edu.ubfc.st.vm.project.grp7.ast.IllFormedNodeException;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaASTVisitor;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;
import edu.ubfc.st.vm.project.grp7.stack.Stack;

import java.io.IOException;

public class MiniJajaInterpreterVisitor extends MiniJajaASTVisitor {
    private final Stack stack;

    public MiniJajaInterpreterVisitor(Stack stack) {
        this.stack = stack;
    }

    @Override
    public void visit(ClasseNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(IdentNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(DeclsNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(VarsNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(VarNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(ArrayNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(CstNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(MethodNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(MainNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(HeadersNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(HeaderNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(InstrsNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(AssignNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(SumNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(IncrementNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(AppelINode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(ReturnNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(WriteNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(WriteLnNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(StringNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(IfNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(WhileNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(ListExpNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(NotNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(AndNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(OrNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(EqualsNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(GreaterNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(PlusNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(SubNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(MinusNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(MultNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(DivNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(AppelENode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(BooleanNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(NumberNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(ArrayItemNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(TypeMethNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(TypeNode node) throws IllFormedNodeException, IOException {

    }
}
