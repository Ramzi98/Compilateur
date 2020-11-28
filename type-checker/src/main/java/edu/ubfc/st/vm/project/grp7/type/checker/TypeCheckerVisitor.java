package edu.ubfc.st.vm.project.grp7.type.checker;

import edu.ubfc.st.vm.project.grp7.ast.IllFormedNodeException;
import edu.ubfc.st.vm.project.grp7.memory.*;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaASTVisitor;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Stack;

public class TypeCheckerVisitor extends MiniJajaASTVisitor {
    public static final String SCOPE_GLOBAL = "global";
    public static final String SCOPE_MAIN = "main";

    Stack<Scope> stack = new Stack<>();
    HashMap<MiniJajaNode,SORTE> miniJajaNodeType = new HashMap<>();
    private Pass pass;
    private Scope scope;

    public void setPass(Pass pass) {
        this.pass = pass;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    private SymbolDictionnary symbolDictionnary;

    public void setDataDictionnary(SymbolDictionnary symbolDictionnary)
    {
        this.symbolDictionnary = symbolDictionnary;
    }

    public HashMap<MiniJajaNode, SORTE> getMiniJajaNodeType() {
        return miniJajaNodeType;
    }

    @Override
    public void visit(ClasseNode node) throws IllFormedNodeException, IOException {
        IdentNode ident = node.identifier();
        MiniJajaNode decls = node.decls();
        MiniJajaNode main = node.methmain();

        if (pass == Pass.FIRST_PASS)
        {
            SymbolDictionnary symbolDictionnary = new SymbolDictionnary();
            symbolDictionnary.register(ident.value(),0);
        }

        stack.push(scope);
        try {
            ident.accept(this);
            stack.push(scope);
            decls.accept(this);
            stack.push(scope);
            main.accept(this);
        } catch (Exception e) {
            throw new IllFormedNodeException(e.toString());
        }

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

        MiniJajaNode nodeHeader = node.header();
        MiniJajaNode nodeHeaders = node.headers();

        try {
            nodeHeader.accept(this);
            nodeHeaders.accept(this);
        } catch (Exception e) {
            throw new IllFormedNodeException(e.toString());
        }

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
        MiniJajaNode identifier = node.identifier();
        try {
            identifier.accept(this);
        } catch (Exception e) {
            throw new IllFormedNodeException(e.toString());
        }

        if (identifier instanceof ArrayItemNode)
        {
            if(miniJajaNodeType.get(identifier) != SORTE.INT){
                throw new IllFormedNodeException(node.line(), node.column(), "Can't increment element of Array with Type "+ miniJajaNodeType.get(identifier));
            }

        }
        else
        {
            if(miniJajaNodeType.get(identifier) != SORTE.INT){
                throw new IllFormedNodeException(node.line(), node.column(), "Can't increment a variable of Type "+ miniJajaNodeType.get(identifier));
            }
        }

        miniJajaNodeType.put(node,SORTE.BOOLEAN);

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

        // SORTE type = SORTE.of(TypeNode.Type.BOOLEAN);
        // miniJajaNodeType.put(node,type);
    }

    @Override
    public void visit(IfNode node) throws IllFormedNodeException, IOException {

        MiniJajaNode expression = node.expression();
        MiniJajaNode nodeTrueInstr = node.trueInstrs();
        MiniJajaNode nodeFalseInstr = node.falseInstrs();

        try {
            expression.accept(this);
        } catch (Exception e) {
            throw new IllFormedNodeException(e.toString());
        }

        if(miniJajaNodeType.get(expression) != SORTE.BOOLEAN){
            throw new IllFormedNodeException(node.line(), node.column(), "Can't evaluate expression with Type "+ miniJajaNodeType.get(expression) +"as a conditional expression.");
        }

        try {
            nodeTrueInstr.accept(this);
            nodeFalseInstr.accept(this);
        } catch (Exception e) {
            throw new IllFormedNodeException(e.toString());
        }



    }

    @Override
    public void visit(WhileNode node) throws IllFormedNodeException, IOException {

        MiniJajaNode expressionWhile = node.expression();
        MiniJajaNode nodeInstr = node.instrs();

        try {
            expressionWhile.accept(this);
        } catch (Exception e) {
            throw new IllFormedNodeException(e.toString());
        }

        if(miniJajaNodeType.get(expressionWhile) != SORTE.BOOLEAN){
            throw new IllFormedNodeException(node.line(), node.column(), "Can't evaluate expression with Type "+ miniJajaNodeType.get(expressionWhile) + "as a conditional expression.");
        }

        try {
            nodeInstr.accept(this);
        } catch (Exception e) {
            throw new IllFormedNodeException(e.toString());
        }
    }

    @Override
    public void visit(ListExpNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(NotNode node) throws IllFormedNodeException, IOException {
        MiniJajaNode expression = node.expression();
        try {
            expression.accept(this);
        } catch (Exception e) {
            throw new IllFormedNodeException(e.toString());
        }

        if(miniJajaNodeType.get(expression) != SORTE.BOOLEAN){

            throw new IllFormedNodeException(node.line(), node.column(), "The type of "+ expression +"Is not compatible with the NOT operator");

        }
        miniJajaNodeType.put(node,SORTE.BOOLEAN);


    }

    @Override
    public void visit(AndNode node) throws IllFormedNodeException, IOException {

        MiniJajaNode leftOperand = node.leftOperand();
        MiniJajaNode rightOperand = node.rightOperand();

        try {
            leftOperand.accept(this);
            rightOperand.accept(this);
        } catch (Exception e) {
            throw new IllFormedNodeException(e.toString());
        }

        if(miniJajaNodeType.get(leftOperand) != SORTE.BOOLEAN){

            throw new IllFormedNodeException(node.line(), node.column(), "The type of "+rightOperand +"Is not compatible with the AND operator");

        }

        if(miniJajaNodeType.get(rightOperand) != SORTE.BOOLEAN){

            throw new IllFormedNodeException(node.line(), node.column(), "The type of "+rightOperand +"Is not compatible with the AND operator");

        }

        miniJajaNodeType.put(node,SORTE.BOOLEAN);

    }

    @Override
    public void visit(OrNode node) throws IllFormedNodeException, IOException {

        MiniJajaNode leftOperand = node.leftOperand();
        MiniJajaNode rightOperand = node.rightOperand();

        try {
            leftOperand.accept(this);
            rightOperand.accept(this);
        } catch (Exception e) {
            throw new IllFormedNodeException(e.toString());
        }

        if (miniJajaNodeType.get(leftOperand) != SORTE.BOOLEAN) {

            throw new IllFormedNodeException(node.line(), node.column(), "The type of " + rightOperand + "Is not compatible with the OR operator");

        }

        if (miniJajaNodeType.get(rightOperand) != SORTE.BOOLEAN) {

            throw new IllFormedNodeException(node.line(), node.column(), "The type of " + rightOperand + "Is not compatible with the OR operator");

        }

        miniJajaNodeType.put(node, SORTE.BOOLEAN);
    }

    @Override
    public void visit(EqualsNode node) throws IllFormedNodeException, IOException {

        MiniJajaNode leftOperandEquals = node.leftOperand();
        MiniJajaNode rightOperandEquals = node.rightOperand();
        try {
            leftOperandEquals.accept(this);
            rightOperandEquals.accept(this);
        } catch (Exception e) {
            throw new IllFormedNodeException(e.toString());
        }

        if (miniJajaNodeType.get(leftOperandEquals) != miniJajaNodeType.get(leftOperandEquals)) {
            throw new IllFormedNodeException(node.line(), node.column(), "The type of " + rightOperandEquals + "can not be compared with the type of "+ leftOperandEquals);
        }
        miniJajaNodeType.put(node, SORTE.BOOLEAN);
    }

    @Override
    public void visit(GreaterNode node) throws IllFormedNodeException, IOException {

        MiniJajaNode leftOperandGreater = node.leftOperand();
        MiniJajaNode rightOperandGreater = node.rightOperand();
        try {
            leftOperandGreater.accept(this);
            rightOperandGreater.accept(this);
        } catch (Exception e) {
            throw new IllFormedNodeException(e.toString());
        }

        if (miniJajaNodeType.get(leftOperandGreater) != SORTE.INT) {
            throw new IllFormedNodeException(node.line(), node.column(), "The type of " + rightOperandGreater + "Is not compatible with the GREATER (>) operator");
        }

        if (miniJajaNodeType.get(rightOperandGreater) != SORTE.INT) {
            throw new IllFormedNodeException(node.line(), node.column(), "The type of " + rightOperandGreater + "Is not compatible with the GREATER (>) operator");
        }
        miniJajaNodeType.put(node, SORTE.BOOLEAN);
    }

    @Override
    public void visit(PlusNode node) throws IllFormedNodeException, IOException {
        MiniJajaNode leftOperandAdd = node.leftOperand();
        MiniJajaNode rightOperandAdd = node.rightOperand();
        try {
            leftOperandAdd.accept(this);
            rightOperandAdd.accept(this);
        } catch (Exception e) {
            throw new IllFormedNodeException(e.toString());
        }

        if (miniJajaNodeType.get(leftOperandAdd) != SORTE.INT) {
            throw new IllFormedNodeException(node.line(), node.column(), "The type of " + rightOperandAdd + "Is not compatible with the ADD operator");
        }

        if (miniJajaNodeType.get(rightOperandAdd) != SORTE.INT) {
            throw new IllFormedNodeException(node.line(), node.column(), "The type of " + rightOperandAdd + "Is not compatible with the ADD operator");
        }
        miniJajaNodeType.put(node, SORTE.INT);
    }

    @Override
    public void visit(SubNode node) throws IllFormedNodeException, IOException {
        MiniJajaNode leftOperandSub = node.leftOperand();
        MiniJajaNode rightOperandSub = node.rightOperand();
        try {
            leftOperandSub.accept(this);
            rightOperandSub.accept(this);
        } catch (Exception e) {
            throw new IllFormedNodeException(e.toString());
        }

        if (miniJajaNodeType.get(leftOperandSub) != SORTE.INT) {

            throw new IllFormedNodeException(node.line(), node.column(), "The type of " + rightOperandSub + "Is not compatible with the SUB operator");

        }

        if (miniJajaNodeType.get(rightOperandSub) != SORTE.INT) {

            throw new IllFormedNodeException(node.line(), node.column(), "The type of " + rightOperandSub + "Is not compatible with the SYB operator");
        }
        miniJajaNodeType.put(node, SORTE.INT);
    }

    @Override
    public void visit(MinusNode node) throws IllFormedNodeException, IOException {
        MiniJajaNode expression = node.expression();
        try {
            expression.accept(this);
        } catch (Exception e) {
            throw new IllFormedNodeException(e.toString());
        }

        if (miniJajaNodeType.get(expression) != SORTE.INT) {

            throw new IllFormedNodeException(node.line(), node.column(), "The type of " + expression + "Is not compatible with the Minus operator");

        }

        miniJajaNodeType.put(node, SORTE.INT);
    }

    @Override
    public void visit(MultNode node) throws IllFormedNodeException, IOException {
        MiniJajaNode leftOperandMult = node.leftOperand();
        MiniJajaNode rightOperandMult = node.rightOperand();
        try {
            leftOperandMult.accept(this);
            rightOperandMult.accept(this);
        } catch (Exception e) {
            throw new IllFormedNodeException(e.toString());
        }

        if (miniJajaNodeType.get(leftOperandMult) != SORTE.INT) {

            throw new IllFormedNodeException(node.line(), node.column(), "The type of " + rightOperandMult + "Is not compatible with the MULT operator");

        }

        if (miniJajaNodeType.get(rightOperandMult) != SORTE.INT) {

            throw new IllFormedNodeException(node.line(), node.column(), "The type of " + rightOperandMult + "Is not compatible with the MULT operator");

        }

        miniJajaNodeType.put(node, SORTE.INT);
    }

    @Override
    public void visit(DivNode node) throws IllFormedNodeException, IOException {
        MiniJajaNode leftOperandDiv = node.leftOperand();
        MiniJajaNode rightOperandDiv = node.rightOperand();
        try {
            leftOperandDiv.accept(this);
            rightOperandDiv.accept(this);
        } catch (Exception e) {
            throw new IllFormedNodeException(e.toString());
        }

        if (miniJajaNodeType.get(leftOperandDiv) != SORTE.INT) {

            throw new IllFormedNodeException(node.line(), node.column(), "The type of " + rightOperandDiv + "Is not compatible with the Div operator");

        }

        if (miniJajaNodeType.get(rightOperandDiv) != SORTE.INT) {

            throw new IllFormedNodeException(node.line(), node.column(), "The type of " + rightOperandDiv + "Is not compatible with the Div operator");

        }

        miniJajaNodeType.put(node, SORTE.INT);

    }

    @Override
    public void visit(AppelENode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(BooleanNode node) throws IllFormedNodeException, IOException {

        if(node.value() != null)
        {
            SORTE type = SORTE.of(TypeNode.Type.BOOLEAN);
            miniJajaNodeType.put(node, type);
        }
        else {
            throw new IllFormedNodeException("Node has no value specified");
        }
    }

    @Override
    public void visit(NumberNode node) throws IllFormedNodeException, IOException {

        if(node.value() != null)
        {
            SORTE type = SORTE.of(TypeNode.Type.INT);
            miniJajaNodeType.put(node,type);
        }
        else
        {
            throw new IllFormedNodeException(node.line(), node.column(), "Node has no value specified");
        }



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
