package edu.ubfc.st.vm.project.grp7.type.checker;

import edu.ubfc.st.vm.project.grp7.memory.SymbolDictionnary;
import edu.ubfc.st.vm.project.grp7.mjj.ast.node.*;
import org.junit.Before;
import org.junit.Test;


public class MethodeSignatureCheckerTest {
    TypeChecker typeChecker;
    SymbolDictionnary symbolDictionnary;
    TypeCheckerVisitor typeCheckerVisitor;

    @Before
    public void start(){
        typeCheckerVisitor = new TypeCheckerVisitor();
        symbolDictionnary = new SymbolDictionnary();
    }


    @Test
    public void MethodeSignatureTypeCheker__First__Second__Pass() throws TypeCheckerException {

        IdentNode identvar1 = IdentNode.builder().value("i").build();
        IdentNode identvark = IdentNode.builder().value("k").build();
        IdentNode identheader = IdentNode.builder().value("j").build();
        IdentNode identfonction = IdentNode.builder().value("fonction").build();
        IdentNode identclasse = IdentNode.builder().value("C").build();

        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();
        NumberNode numberNode = NumberNode.builder().value(2).build();
        NumberNode numberNode0 = NumberNode.builder().value(0).build();
        BooleanNode booleanNode = BooleanNode.builder().value(true).build();

        TypeNode type = TypeNode.builder().value(TypeNode.Type.BOOLEAN).build();

        VarNode varNode = VarNode.builder().typeMeth(typeMethNode).identifier(identvar1).expression(numberNode).build();
        VarsNode varsNode = VarsNode.builder().var(varNode).vars(null).build();

        VarNode varNode1 = VarNode.builder().typeMeth(typeMethNode).identifier(identvark).expression(numberNode0).build();
        VarsNode varsNode1 = VarsNode.builder().var(varNode1).vars(null).build();

        IncrementNode incrementNode = IncrementNode.builder().identifier(identvar1).build();

        ReturnNode returnNode = ReturnNode.builder().ret(identvar1).build();
        InstrsNode instrsReturn = InstrsNode.builder().instruction(returnNode).instrs(null).build();
        InstrsNode instrsNode = InstrsNode.builder().instruction(incrementNode).instrs(instrsReturn).build();

        HeaderNode headerNode = HeaderNode.builder()
                .type(type)
                .identifier(identheader)
                .build();

        HeadersNode headersNode = HeadersNode.builder()
                .header(headerNode)
                .headers(null)
                .build();

        ListExpNode listExpNode = ListExpNode.builder().expression(booleanNode).listexp(null).build();

        AppelENode appelENode = AppelENode.builder().identifier(identfonction).listexp(listExpNode).build();

        AssignNode assignNode = AssignNode.builder().identifier(identvark).expression(appelENode).build();

        InstrsNode maininstrs = InstrsNode.builder().instruction(assignNode).instrs(null).build();


        MainNode mainNode = MainNode.builder().vars(varsNode1).instrs(maininstrs).build();

        MethodNode methodNode = MethodNode.builder().typeMeth(typeMethNode).identifier(identfonction).headers(headersNode).vars(varsNode).instrs(instrsNode).build();

        DeclsNode declsNode = DeclsNode.builder().decl(methodNode).decls(null).build();

        ClasseNode classeNode1 = ClasseNode.builder().identifier(identclasse).decls(declsNode).methmain(mainNode).build();

        typeChecker = new TypeCheckerImpl(classeNode1);
        typeChecker.typeCheck();

    }


    @Test(expected = TypeCheckerException.class)
    public void MethodeSignatureTypeCheker__Exception__Dif__Signature__MethodDecl__MethodCall() throws TypeCheckerException {

        IdentNode identvar1 = IdentNode.builder().value("i").build();
        IdentNode identvark = IdentNode.builder().value("k").build();
        IdentNode identheader = IdentNode.builder().value("j").build();
        IdentNode identfonction = IdentNode.builder().value("fonction").build();
        IdentNode identclasse = IdentNode.builder().value("C").build();

        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();
        NumberNode numberNode = NumberNode.builder().value(2).build();
        NumberNode numberNode0 = NumberNode.builder().value(0).build();

        TypeNode type = TypeNode.builder().value(TypeNode.Type.BOOLEAN).build();

        VarNode varNode = VarNode.builder().typeMeth(typeMethNode).identifier(identvar1).expression(numberNode).build();
        VarsNode varsNode = VarsNode.builder().var(varNode).vars(null).build();

        VarNode varNode1 = VarNode.builder().typeMeth(typeMethNode).identifier(identvark).expression(numberNode0).build();
        VarsNode varsNode1 = VarsNode.builder().var(varNode1).vars(null).build();

        IncrementNode incrementNode = IncrementNode.builder().identifier(identvar1).build();

        ReturnNode returnNode = ReturnNode.builder().ret(identvar1).build();
        InstrsNode instrsReturn = InstrsNode.builder().instruction(returnNode).instrs(null).build();
        InstrsNode instrsNode = InstrsNode.builder().instruction(incrementNode).instrs(instrsReturn).build();

        HeaderNode headerNode = HeaderNode.builder()
                .type(type)
                .identifier(identheader)
                .build();

        HeadersNode headersNode = HeadersNode.builder()
                .header(headerNode)
                .headers(null)
                .build();

        ListExpNode listExpNode = ListExpNode.builder().expression(numberNode).listexp(null).build();

        AppelENode appelENode = AppelENode.builder().identifier(identfonction).listexp(listExpNode).build();
        AssignNode assignNode = AssignNode.builder().identifier(identvark).expression(appelENode).build();

        InstrsNode maininstrs = InstrsNode.builder().instruction(assignNode).instrs(null).build();


        MainNode mainNode = MainNode.builder().vars(varsNode1).instrs(maininstrs).build();

        MethodNode methodNode = MethodNode.builder().typeMeth(typeMethNode).identifier(identfonction).headers(headersNode).vars(varsNode).instrs(instrsNode).build();

        DeclsNode declsNode = DeclsNode.builder().decl(methodNode).decls(null).build();

        ClasseNode classeNode1 = ClasseNode.builder().identifier(identclasse).decls(declsNode).methmain(mainNode).build();

        typeChecker = new TypeCheckerImpl(classeNode1);
        typeChecker.typeCheck();

    }
}
