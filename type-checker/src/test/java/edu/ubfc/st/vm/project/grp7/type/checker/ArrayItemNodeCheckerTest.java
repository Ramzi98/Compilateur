package edu.ubfc.st.vm.project.grp7.type.checker;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.ast.IllFormedNodeException;
import edu.ubfc.st.vm.project.grp7.memory.SymbolDictionnary;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class ArrayItemNodeCheckerTest {

    TypeChecker typeChecker;
    SymbolDictionnary symbolDictionnary;
    TypeCheckerVisitor typeCheckerVisitor;

    @Before
    public void start(){

        typeCheckerVisitor = new TypeCheckerVisitor();
        symbolDictionnary = new SymbolDictionnary();

    }

    @Test
    public void ArrayItemNodeTypeCheck() throws IOException, IllFormedNodeException {


        IdentNode identvar = IdentNode.builder().value("i").build();
        IdentNode identclasse = IdentNode.builder().value("C").build();
        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();
        NumberNode numberNode = NumberNode.builder().value(5).build();
        ArrayNode node = ArrayNode.builder().typeMeth(typeMethNode).identifier(identvar).expression(numberNode).build();

        ArrayItemNode nodeArray = ArrayItemNode.builder().identifier(identvar).expression(numberNode).build();


        PlusNode plus = PlusNode.builder().leftOperand(nodeArray).rightOperand(numberNode).build();

        InstrsNode instrMain = InstrsNode.builder().instruction(plus).instrs(null).build();
        DeclsNode declsNode = DeclsNode.builder().decl(node).decls(null).build();
        MainNode mainNode = MainNode.builder().vars(null).instrs(instrMain).build();
        ClasseNode classeNode1 = ClasseNode.builder().identifier(identclasse).decls(declsNode).methmain(mainNode).build();

        typeChecker = new TypeCheckerImpl(classeNode1);
        typeChecker.typeCheck();

    }

    @Test(expected = IllFormedNodeException.class)
    public void ArrayItemNodeTypeCheck__ExpressionInvalid__WithException() throws IOException, IllFormedNodeException {


        IdentNode identvar = IdentNode.builder().value("i").build();
        IdentNode identclasse = IdentNode.builder().value("C").build();
        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();
        NumberNode numberNode2 = NumberNode.builder().build();
        NumberNode numberNode = NumberNode.builder().value(5).build();

        ArrayNode node = ArrayNode.builder().typeMeth(typeMethNode).identifier(identvar).expression(numberNode).build();

        ArrayItemNode nodeArray = ArrayItemNode.builder().identifier(identvar).expression(numberNode2).build();


        PlusNode plus = PlusNode.builder().leftOperand(nodeArray).rightOperand(numberNode).build();

        InstrsNode instrMain = InstrsNode.builder().instruction(plus).instrs(null).build();
        DeclsNode declsNode = DeclsNode.builder().decl(node).decls(null).build();
        MainNode mainNode = MainNode.builder().vars(null).instrs(instrMain).build();
        ClasseNode classeNode1 = ClasseNode.builder().identifier(identclasse).decls(declsNode).methmain(mainNode).build();

        typeChecker = new TypeCheckerImpl(classeNode1);
        typeChecker.typeCheck();

    }

    @Test(expected = IllFormedNodeException.class)
    public void ArrayItemNodeTypeCheck__ExpressionNotInt__WithException() throws IOException, IllFormedNodeException {


        IdentNode identvar = IdentNode.builder().value("i").build();
        IdentNode identclasse = IdentNode.builder().value("C").build();
        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();
        BooleanNode booleanNode = BooleanNode.builder().value(true).build();
        NumberNode numberNode = NumberNode.builder().value(5).build();
        ArrayNode node = ArrayNode.builder().typeMeth(typeMethNode).identifier(identvar).expression(numberNode).build();

        ArrayItemNode nodeArray = ArrayItemNode.builder().identifier(identvar).expression(booleanNode).build();


        PlusNode plus = PlusNode.builder().leftOperand(nodeArray).rightOperand(numberNode).build();

        InstrsNode instrMain = InstrsNode.builder().instruction(plus).instrs(null).build();
        DeclsNode declsNode = DeclsNode.builder().decl(node).decls(null).build();
        MainNode mainNode = MainNode.builder().vars(null).instrs(instrMain).build();
        ClasseNode classeNode1 = ClasseNode.builder().identifier(identclasse).decls(declsNode).methmain(mainNode).build();

        typeChecker = new TypeCheckerImpl(classeNode1);
        typeChecker.typeCheck();
    }


    @Test(expected = IllFormedNodeException.class)
    public void ArrayItemNodeTypeCheck__ArrayIdentUnexistant__WithException() throws IOException, IllFormedNodeException {


        IdentNode identvar = IdentNode.builder().value("i").build();
        IdentNode identUnexistant = IdentNode.builder().value("j").build();
        IdentNode identclasse = IdentNode.builder().value("C").build();
        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();
        NumberNode numberNode = NumberNode.builder().value(5).build();
        ArrayNode node = ArrayNode.builder().typeMeth(typeMethNode).identifier(identvar).expression(numberNode).build();

        ArrayItemNode nodeArray = ArrayItemNode.builder().identifier(identUnexistant).expression(numberNode).build();


        PlusNode plus = PlusNode.builder().leftOperand(nodeArray).rightOperand(numberNode).build();

        InstrsNode instrMain = InstrsNode.builder().instruction(plus).instrs(null).build();
        DeclsNode declsNode = DeclsNode.builder().decl(node).decls(null).build();
        MainNode mainNode = MainNode.builder().vars(null).instrs(instrMain).build();
        ClasseNode classeNode1 = ClasseNode.builder().identifier(identclasse).decls(declsNode).methmain(mainNode).build();

        typeChecker = new TypeCheckerImpl(classeNode1);
        typeChecker.typeCheck();

    }




}
