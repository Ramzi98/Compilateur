package edu.ubfc.st.vm.project.grp7.type.checker;

import edu.ubfc.st.vm.project.grp7.ast.IllFormedNodeException;
import edu.ubfc.st.vm.project.grp7.memory.*;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaASTVisitor;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;

import java.io.IOException;
import java.util.Stack;

public class TypeCheckerVisitor extends MiniJajaASTVisitor {
    public static final int FIRST_PASS = 0;
    public static final int SECOND_PASS = 1;

    public static final String SCOPE_GLOBAL = "global";
    public static final String SCOPE_MAIN = "main";

    Stack<Scope> stack = new Stack<>();

    private Pass pass;
    private Scope scope;

    public void setPass(Pass pass) {
        this.pass = pass;
    }

    public void setScope(Scope scope)
    {
        this.scope = scope;
    }

    private SymbolDictionnary symbolDictionnary;

    public void setDataDictionnary(SymbolDictionnary symbolDictionnary)
    {
        this.symbolDictionnary = symbolDictionnary;
    }

    @Override
    public void visit(ClasseNode node) throws IllFormedNodeException, IOException {
        IdentNode ident = (IdentNode) node.identifier();
        MiniJajaNode decls = node.decls();
        MiniJajaNode main = node.methmain();

        Scope scope = Scope.GLOBAL;



        if (pass == Pass.FIRST_PASS)
        {

        }

        stack.push(scope);
        try {
            ident.accept(this);
            stack.push(scope);
            decls.accept(this);
            stack.push(scope);
            main.accept(this);
        } catch (Exception e) {
            try {
                throw new TypeChekerException(e);
            } catch (TypeChekerException typeChekerException) {
                typeChekerException.printStackTrace();
            }
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
