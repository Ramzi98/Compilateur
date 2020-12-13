package edu.ubfc.st.vm.project.grp7.type.checker;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.ast.IllFormedNodeException;
import edu.ubfc.st.vm.project.grp7.memory.SymbolDictionnary;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AssignNodeCheckerTest {


    TypeChecker typeChecker;
    TypeCheckerVisitor typeCheckerVisitor;
    SymbolDictionnary symbolDictionnary;

    @Before
    public void start(){

        typeCheckerVisitor = new TypeCheckerVisitor();
        symbolDictionnary = new SymbolDictionnary();

    }

    @Test
    public void AssignNodeTypeCheck() throws IOException, IllFormedNodeException {

        IdentNode identvar1 = IdentNode.builder().value("i").build();

        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();
        NumberNode numberNode = NumberNode.builder().value(2).build();

        VarNode varNode = VarNode.builder().typeMeth(typeMethNode).identifier(identvar1).expression(numberNode).build();
        VarsNode varsNode = VarsNode.builder().var(varNode).vars(null).build();

        NumberNode numberNode2 = NumberNode.builder().value(3).build();
        AssignNode assignNode = AssignNode.builder().identifier(identvar1).expression(numberNode2).build();

        InstrsNode instrsNode = InstrsNode.builder().instruction(assignNode).instrs(null).build();


        MainNode mainNode = MainNode.builder().vars(varsNode).instrs(instrsNode).build();

        typeChecker = new TypeCheckerImpl(mainNode);
        typeChecker.typeCheck();
    }
    @Test(expected = IllFormedNodeException.class)
    public void AssignNodeTypeCheck__WithException__NotDeclared() throws IOException, IllFormedNodeException {

        IdentNode identvar = IdentNode.builder().value("i").build();
        TypeNode typeNode = TypeNode.builder().value(TypeNode.Type.INT).build();
        NumberNode numberNode = NumberNode.builder().value(2).build();

        CstNode cstNode = CstNode.builder().type(typeNode).identifier(identvar).expression(numberNode).build();
        NumberNode numberNode2 = NumberNode.builder().value(3).build();
        AssignNode assign = AssignNode.builder().identifier(identvar).expression(numberNode2).build();
        typeChecker = new TypeCheckerImpl(assign);
        typeChecker.typeCheck();


    }
    @Test(expected = IllFormedNodeException.class)
    public void AssignNodeTypeCheck__WithConstantException() throws IOException, IllFormedNodeException {

        IdentNode identvar = IdentNode.builder().value("i").build();
        TypeNode typeNode = TypeNode.builder().value(TypeNode.Type.INT).build();
        NumberNode numberNode = NumberNode.builder().value(2).build();

        CstNode cstNode = CstNode.builder().type(typeNode).identifier(identvar).expression(numberNode).build();
        VarsNode varsNode = VarsNode.builder().var(cstNode).vars(null).build();

        NumberNode numberNode2 = NumberNode.builder().value(3).build();
        AssignNode assignNode = AssignNode.builder().identifier(identvar).expression(numberNode2).build();

        InstrsNode instrsNode = InstrsNode.builder().instruction(assignNode).instrs(null).build();


        MainNode mainNode = MainNode.builder().vars(varsNode).instrs(instrsNode).build();
        typeChecker = new TypeCheckerImpl(mainNode);
        typeChecker.typeCheck();

    }

    @Test(expected = IllFormedNodeException.class)
    public void AssignNodeTypeCheck__WithMethodException() throws IOException, IllFormedNodeException {

        IdentNode identvar1 = IdentNode.builder().value("i").build();
        IdentNode identheader = IdentNode.builder().value("j").build();
        IdentNode identfonction = IdentNode.builder().value("fonction").build();
        IdentNode identclasse = IdentNode.builder().value("C").build();

        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();
        NumberNode numberNode = NumberNode.builder().value(2).build();

        TypeNode type = TypeNode.builder().value(TypeNode.Type.BOOLEAN).build();


        VarNode varNode = VarNode.builder().typeMeth(typeMethNode).identifier(identvar1).expression(numberNode).build();
        VarsNode varsNode = VarsNode.builder().var(varNode).vars(null).build();

        IncrementNode incrementNode = IncrementNode.builder().identifier(identvar1).build();

        InstrsNode instrsNode = InstrsNode.builder().instruction(incrementNode).instrs(null).build();

        HeaderNode headerNode = HeaderNode.builder()
                .type(type)
                .identifier(identheader)
                .build();

        HeadersNode headersNode = HeadersNode.builder()
                .header(headerNode)
                .headers(null)
                .build();

        AssignNode assignMethod = AssignNode.builder().identifier(identfonction).expression(numberNode).build();


        InstrsNode instrMain = InstrsNode.builder().instruction(assignMethod).instrs(null).build();
        MainNode mainNode = MainNode.builder().vars(null).instrs(instrMain).build();

        MethodNode methodNode = MethodNode.builder().typeMeth(typeMethNode).vars(varsNode).identifier(identfonction).headers(headersNode).instrs(instrsNode).build();

        DeclsNode declsNode = DeclsNode.builder().decl(methodNode).decls(null).build();

        ClasseNode classeNode1 = ClasseNode.builder().identifier(identclasse).decls(declsNode).methmain(mainNode).build();

        typeChecker = new TypeCheckerImpl(mainNode);
        typeChecker.typeCheck();

    }

    @Test(expected = IllFormedNodeException.class)
    public void AssignNodeTypeCheck__WithTabException() throws IOException, IllFormedNodeException {

        IdentNode identvar = IdentNode.builder().value("i").build();
        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();
        NumberNode numberNode = NumberNode.builder().value(5).build();
        ArrayNode node = ArrayNode.builder().typeMeth(typeMethNode).identifier(identvar).expression(numberNode).build();


        VarsNode varsNode = VarsNode.builder().var(node).vars(null).build();

        NumberNode numberNode2 = NumberNode.builder().build();
        AssignNode assignNode = AssignNode.builder().identifier(identvar).expression(numberNode2).build();

        InstrsNode instrsNode = InstrsNode.builder().instruction(assignNode).instrs(null).build();


        MainNode mainNode = MainNode.builder().vars(varsNode).instrs(instrsNode).build();

        typeChecker = new TypeCheckerImpl(mainNode);
        typeChecker.typeCheck();


    }

    @Test(expected = IllFormedNodeException.class)
    public void AssignNodeTypeCheck__WithTabIncompatibleTypeException() throws IOException, IllFormedNodeException {

        IdentNode identvar = IdentNode.builder().value("i").build();
        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.BOOLEAN).build();
        NumberNode numberNode = NumberNode.builder().value(5).build();
        ArrayNode node = ArrayNode.builder().typeMeth(typeMethNode).identifier(identvar).expression(numberNode).build();


        VarsNode varsNode = VarsNode.builder().var(node).vars(null).build();

        NumberNode numberNode2 = NumberNode.builder().value(5).build();
        AssignNode assignNode = AssignNode.builder().identifier(identvar).expression(numberNode2).build();

        InstrsNode instrsNode = InstrsNode.builder().instruction(assignNode).instrs(null).build();


        MainNode mainNode = MainNode.builder().vars(varsNode).instrs(instrsNode).build();

        typeChecker = new TypeCheckerImpl(mainNode);
        typeChecker.typeCheck();


    }

    @Test(expected = IllFormedNodeException.class)
    public void AssignNodeTypeCheck__IncompatibleType__WithException() throws IOException, IllFormedNodeException {

        IdentNode identvar1 = IdentNode.builder().value("i").build();

        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();
        NumberNode numberNode = NumberNode.builder().value(2).build();

        VarNode varNode = VarNode.builder().typeMeth(typeMethNode).identifier(identvar1).expression(numberNode).build();
        VarsNode varsNode = VarsNode.builder().var(varNode).vars(null).build();

        BooleanNode numberNode2 = BooleanNode.builder().value(false).build();
        AssignNode assignNode = AssignNode.builder().identifier(identvar1).expression(numberNode2).build();

        InstrsNode instrsNode = InstrsNode.builder().instruction(assignNode).instrs(null).build();


        MainNode mainNode = MainNode.builder().vars(varsNode).instrs(instrsNode).build();

        typeChecker = new TypeCheckerImpl(mainNode);
        typeChecker.typeCheck();
    }

    @Test
    public void AssignNodeTypeCheck__Table() throws IOException, IllFormedNodeException {

        IdentNode identvar = IdentNode.builder().value("i").build();
        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();
        NumberNode numberNode = NumberNode.builder().value(5).build();
        ArrayNode node = ArrayNode.builder().typeMeth(typeMethNode).identifier(identvar).expression(numberNode).build();

        ArrayItemNode nodeArray = ArrayItemNode.builder().identifier(identvar).expression(numberNode).build();

        VarsNode varsNode = VarsNode.builder().var(node).vars(null).build();

        NumberNode numberNode2 = NumberNode.builder().value(5).build();
        AssignNode assignNode = AssignNode.builder().identifier(nodeArray).expression(numberNode2).build();

        InstrsNode instrsNode = InstrsNode.builder().instruction(assignNode).instrs(null).build();


        MainNode mainNode = MainNode.builder().vars(varsNode).instrs(instrsNode).build();

        typeChecker = new TypeCheckerImpl(mainNode);
        typeChecker.typeCheck();
    }

    @Test(expected = IllFormedNodeException.class)
    public void AssignNodeTypeCheck__Table__InvalidExpression__WithException() throws IOException, IllFormedNodeException {

        IdentNode identvar = IdentNode.builder().build();
        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();
        NumberNode numberNode = NumberNode.builder().build();
        ArrayNode node = ArrayNode.builder().typeMeth(typeMethNode).identifier(identvar).expression(numberNode).build();

        ArrayItemNode nodeArray = ArrayItemNode.builder().identifier(identvar).expression(numberNode).build();

        VarsNode varsNode = VarsNode.builder().var(node).vars(null).build();

        NumberNode numberNode2 = NumberNode.builder().value(5).build();
        AssignNode assignNode = AssignNode.builder().identifier(nodeArray).expression(numberNode2).build();

        InstrsNode instrsNode = InstrsNode.builder().instruction(assignNode).instrs(null).build();


        MainNode mainNode = MainNode.builder().vars(varsNode).instrs(instrsNode).build();

        typeChecker = new TypeCheckerImpl(mainNode);
        typeChecker.typeCheck();
    }

    @Test
    public void AssignNodeTypeCheck__Table__InvalidIdentifier__WithException() throws IOException, IllFormedNodeException {

        IdentNode identvar = IdentNode.builder().value("i").build();
        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();
        NumberNode numberNode = NumberNode.builder().value(6).build();
        ArrayNode node = ArrayNode.builder().typeMeth(typeMethNode).identifier(identvar).expression(numberNode).build();

        ArrayItemNode nodeArray = ArrayItemNode.builder().identifier(identvar).expression(numberNode).build();

        VarsNode varsNode = VarsNode.builder().var(node).vars(null).build();

        NumberNode numberNode2 = NumberNode.builder().value(5).build();
        AssignNode assignNode = AssignNode.builder().identifier(nodeArray).expression(numberNode2).build();

        InstrsNode instrsNode = InstrsNode.builder().instruction(assignNode).instrs(null).build();


        MainNode mainNode = MainNode.builder().vars(varsNode).instrs(instrsNode).build();

        typeChecker = new TypeCheckerImpl(mainNode);
        typeChecker.typeCheck();
    }
    @Test(expected = IllFormedNodeException.class)
    public void AssignNodeTypeCheck__Table__IncompatibleTypes__WithException() throws IOException, IllFormedNodeException {

        IdentNode identvar = IdentNode.builder().value("i").build();
        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();
        NumberNode numberNode = NumberNode.builder().value(5).build();
        ArrayNode node = ArrayNode.builder().typeMeth(typeMethNode).identifier(identvar).expression(numberNode).build();

        ArrayItemNode nodeArray = ArrayItemNode.builder().identifier(identvar).expression(numberNode).build();

        VarsNode varsNode = VarsNode.builder().var(node).vars(null).build();

        BooleanNode numberNode2 = BooleanNode.builder().value(false).build();
        AssignNode assignNode = AssignNode.builder().identifier(nodeArray).expression(numberNode2).build();

        InstrsNode instrsNode = InstrsNode.builder().instruction(assignNode).instrs(null).build();


        MainNode mainNode = MainNode.builder().vars(varsNode).instrs(instrsNode).build();

        typeChecker = new TypeCheckerImpl(mainNode);
        typeChecker.typeCheck();
    }

    @Test(expected = IllFormedNodeException.class)
    public void AssignNodeTypeCheck__Table__VConstantReassign__WithException() throws IOException, IllFormedNodeException {

        IdentNode identvar = IdentNode.builder().value("i").build();
        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();
        NumberNode numberNode = NumberNode.builder().value(5).build();
        ArrayNode node = ArrayNode.builder().typeMeth(typeMethNode).identifier(identvar).expression(numberNode).build();

        ArrayItemNode nodeArray = ArrayItemNode.builder().identifier(identvar).expression(numberNode).build();

        VarsNode varsNode = VarsNode.builder().var(node).vars(null).build();

        BooleanNode numberNode2 = BooleanNode.builder().value(false).build();
        AssignNode assignNode = AssignNode.builder().identifier(nodeArray).expression(numberNode2).build();

        InstrsNode instrsNode = InstrsNode.builder().instruction(assignNode).instrs(null).build();


        MainNode mainNode = MainNode.builder().vars(varsNode).instrs(instrsNode).build();

        typeChecker = new TypeCheckerImpl(mainNode);
        typeChecker.typeCheck();
    }




}
