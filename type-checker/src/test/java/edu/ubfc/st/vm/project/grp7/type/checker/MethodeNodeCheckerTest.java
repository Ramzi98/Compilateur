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

public class MethodeNodeCheckerTest {

    TypeChecker typeChecker;
    SymbolDictionnary symbolDictionnary;
    TypeCheckerVisitor typeCheckerVisitor;

    @Before
    public void start(){
        typeCheckerVisitor = new TypeCheckerVisitor();
        symbolDictionnary = new SymbolDictionnary();
    }


     @Test
    public void MethodeNodeTypeCheker__First__Second__Pass() throws IllFormedNodeException {

        IdentNode identvar1 = IdentNode.builder().value("i").build();
        IdentNode identheader = IdentNode.builder().value("j").build();
        IdentNode identfonction = IdentNode.builder().value("fonction").build();
        IdentNode identclasse = IdentNode.builder().value("C").build();

        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();
        NumberNode numberNode = NumberNode.builder().value(2).build();

        TypeNode type = TypeNode.builder().value(TypeNode.Type.BOOLEAN).build();


        VarNode varNode = VarNode.builder().typeMeth(typeMethNode).identifier(identvar1).expression(numberNode).build();
        VarsNode varsNode = VarsNode.builder().var(varNode).vars(null).build();

        VarNode varNode1 = VarNode.builder().identifier(identfonction).typeMeth(typeMethNode).expression(numberNode).build();
        VarsNode varsDecls = VarsNode.builder().var(varNode1).vars(null).build();
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



        DeclsNode declsNode2 = DeclsNode.builder().decl(varsDecls).decls(null).build();
        MainNode mainNode = MainNode.builder().vars(null).instrs(null).build();

        MethodNode methodNode = MethodNode.builder().typeMeth(typeMethNode).vars(varsNode).identifier(identfonction).headers(headersNode).instrs(instrsNode).build();

        DeclsNode declsNode = DeclsNode.builder().decl(methodNode).decls(declsNode2).build();

        ClasseNode classeNode1 = ClasseNode.builder().identifier(identclasse).decls(declsNode).methmain(mainNode).build();

        typeChecker = new TypeChecker(classeNode1);
        typeChecker.setsymbolDictionnary(symbolDictionnary);
        typeChecker.typeCheck();


    }



     @Test(expected = IllFormedNodeException.class)
    public void MethodeNodeTypeCheker__Exception__OMEGA() throws IllFormedNodeException {

        IdentNode identvar1 = IdentNode.builder().value("i").build();
        IdentNode identheader = IdentNode.builder().value("j").build();
        IdentNode identfonction = IdentNode.builder().value("fonction").build();
        IdentNode identclasse = IdentNode.builder().value("C").build();

        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();
        TypeMethNode typeMethNode1 = TypeMethNode.builder().value(TypeMethNode.TypeMeth.VOID).build();
        NumberNode numberNode = NumberNode.builder().value(2).build();

        ReturnNode returnNode = ReturnNode.builder().ret(numberNode).build();

        TypeNode type = TypeNode.builder().value(TypeNode.Type.BOOLEAN).build();


        VarNode varNode = VarNode.builder().typeMeth(typeMethNode).identifier(identvar1).expression(numberNode).build();
        VarsNode varsNode = VarsNode.builder().var(varNode).vars(null).build();

        IncrementNode incrementNode = IncrementNode.builder().identifier(identvar1).build();

        InstrsNode instrsNode2 = InstrsNode.builder().instruction(returnNode).instrs(null).build();

        InstrsNode instrsNode = InstrsNode.builder().instruction(incrementNode).instrs(instrsNode2).build();

        HeaderNode headerNode = HeaderNode.builder()
                .type(type)
                .identifier(identheader)
                .build();

        HeadersNode headersNode = HeadersNode.builder()
                .header(headerNode)
                .headers(null)
                .build();

        MainNode mainNode = MainNode.builder().vars(null).instrs(null).build();

        MethodNode methodNode = MethodNode.builder().typeMeth(typeMethNode1).vars(varsNode).identifier(identfonction).headers(headersNode).instrs(instrsNode).build();

        DeclsNode declsNode = DeclsNode.builder().decl(methodNode).decls(null).build();

        ClasseNode classeNode1 = ClasseNode.builder().identifier(identclasse).decls(declsNode).methmain(mainNode).build();

        typeChecker = new TypeChecker(classeNode1);
        typeChecker.setsymbolDictionnary(symbolDictionnary);
        typeChecker.typeCheck();


        assertThat(symbolDictionnary.find(identvar1.value()),is(-1));

    }




}
