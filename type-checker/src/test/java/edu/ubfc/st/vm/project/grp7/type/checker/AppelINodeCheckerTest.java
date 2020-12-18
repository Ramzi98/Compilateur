package edu.ubfc.st.vm.project.grp7.type.checker;

import edu.ubfc.st.vm.project.grp7.memory.SymbolDictionnary;
import edu.ubfc.st.vm.project.grp7.mjj.ast.node.*;
import org.junit.Before;
import org.junit.Test;


public class AppelINodeCheckerTest {


    TypeChecker typeChecker;
    SymbolDictionnary symbolDictionnary;
    TypeCheckerVisitor typeCheckerVisitor;

    @Before
    public void start() {
        typeCheckerVisitor = new TypeCheckerVisitor();
        symbolDictionnary = new SymbolDictionnary();
    }


    @Test
    public void AppelINodeTypeCheker() throws TypeCheckerException {
        IdentNode identvar1 = IdentNode.builder().value("i").build();

        IdentNode identheader = IdentNode.builder().value("j").build();
        IdentNode identfonction = IdentNode.builder().value("fonction").build();
        IdentNode identclasse = IdentNode.builder().value("C").build();

        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();
        NumberNode numberNode = NumberNode.builder().value(2).build();
        BooleanNode booleanNode = BooleanNode.builder().value(true).build();

        TypeNode type = TypeNode.builder().value(TypeNode.Type.BOOLEAN).build();


        VarNode varNode = VarNode.builder().typeMeth(typeMethNode).identifier(identvar1).expression(numberNode).build();
        VarsNode varsNode = VarsNode.builder().var(varNode).vars(null).build();

        IncrementNode incrementNode = IncrementNode.builder().identifier(identvar1).build();

        ReturnNode returnNode = ReturnNode.builder().ret(incrementNode).build();
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
        AppelINode appelINode = AppelINode.builder().identifier(identfonction).listexp(listExpNode).build();
        InstrsNode instrs = InstrsNode.builder().instruction(appelINode).instrs(null).build();

        MethodNode methodNode = MethodNode.builder().typeMeth(typeMethNode).vars(varsNode).identifier(identfonction).headers(headersNode).instrs(instrsNode).build();
        MainNode mainNode = MainNode.builder().vars(null).instrs(instrs).build();



        DeclsNode declsNode = DeclsNode.builder().decl(methodNode).decls(null).build();

        ClasseNode classeNode1 = ClasseNode.builder().identifier(identclasse).decls(declsNode).methmain(mainNode).build();

        typeChecker = new TypeCheckerImpl(classeNode1);
        typeChecker.typeCheck();
    }

    @Test(expected = TypeCheckerException.class)
    public void AppelINodeTypeCheker__WithException_NoIdentDeclared() throws TypeCheckerException {
        IdentNode identvar1 = IdentNode.builder().value("i").build();

        IdentNode identheader = IdentNode.builder().value("j").build();
        IdentNode identfonction = IdentNode.builder().value("fonction").build();
        IdentNode fakeidentfonction = IdentNode.builder().value("func").build();
        IdentNode identclasse = IdentNode.builder().value("C").build();

        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();
        NumberNode numberNode = NumberNode.builder().value(2).build();
        BooleanNode booleanNode = BooleanNode.builder().value(true).build();

        TypeNode type = TypeNode.builder().value(TypeNode.Type.BOOLEAN).build();


        VarNode varNode = VarNode.builder().typeMeth(typeMethNode).identifier(identvar1).expression(numberNode).build();
        VarsNode varsNode = VarsNode.builder().var(varNode).vars(null).build();

        IncrementNode incrementNode = IncrementNode.builder().identifier(identvar1).build();

        ReturnNode returnNode = ReturnNode.builder().ret(varNode).build();
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
        AppelINode appelINode = AppelINode.builder().identifier(fakeidentfonction).listexp(listExpNode).build();
        InstrsNode instrs = InstrsNode.builder().instruction(appelINode).instrs(null).build();

        MethodNode methodNode = MethodNode.builder().typeMeth(typeMethNode).vars(varsNode).identifier(identfonction).headers(headersNode).instrs(instrsNode).build();
        MainNode mainNode = MainNode.builder().vars(null).instrs(instrs).build();



        DeclsNode declsNode = DeclsNode.builder().decl(methodNode).decls(null).build();

        ClasseNode classeNode1 = ClasseNode.builder().identifier(identclasse).decls(declsNode).methmain(mainNode).build();

        typeChecker = new TypeCheckerImpl(classeNode1);
        typeChecker.typeCheck();
    }



}
