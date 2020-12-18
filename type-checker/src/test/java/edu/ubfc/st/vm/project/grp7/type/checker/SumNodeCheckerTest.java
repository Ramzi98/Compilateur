package edu.ubfc.st.vm.project.grp7.type.checker;

import edu.ubfc.st.vm.project.grp7.memory.SymbolDictionnary;
import edu.ubfc.st.vm.project.grp7.mjj.ast.node.*;
import org.junit.Before;
import org.junit.Test;

public class SumNodeCheckerTest {

    TypeChecker typeChecker;
    SymbolDictionnary symbolDictionnary;
    TypeCheckerVisitor typeCheckerVisitor;

    @Before
    public void start(){
        typeCheckerVisitor = new TypeCheckerVisitor();
        symbolDictionnary = new SymbolDictionnary();
    }

    @Test(expected = TypeCheckerException.class)
    public void SumNodeTypeCheker__Exception__Type_Identifier_NOT_INT() throws TypeCheckerException {

        IdentNode identvar1 = IdentNode.builder().value("i").build();

        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.BOOLEAN).build();
        BooleanNode booleanNode = BooleanNode.builder().value(Boolean.TRUE).build();
        NumberNode numberNode = NumberNode.builder().value(6).build();

        VarNode varNode = VarNode.builder().typeMeth(typeMethNode).identifier(identvar1).expression(booleanNode).build();
        VarsNode varsNode = VarsNode.builder().var(varNode).vars(null).build();

        SumNode sumNode = SumNode.builder().identifier(identvar1).expression(numberNode).build();

        InstrsNode instrsNode = InstrsNode.builder().instruction(sumNode).instrs(null).build();


        MainNode mainNode = MainNode.builder().vars(varsNode).instrs(instrsNode).build();

        typeChecker = new TypeCheckerImpl(mainNode);
        typeChecker.typeCheck();


    }

    @Test(expected = TypeCheckerException.class)
    public void SumNodeTypeCheker__Exception__Type_Expression_NOT_INT() throws TypeCheckerException {

        IdentNode identvar1 = IdentNode.builder().value("i").build();

        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.BOOLEAN).build();
        TypeMethNode typeMethNode2 = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();
        BooleanNode booleanNode = BooleanNode.builder().value(Boolean.TRUE).build();
        NumberNode numberNode = NumberNode.builder().value(6).build();

        VarNode varNode = VarNode.builder().typeMeth(typeMethNode2).identifier(identvar1).expression(numberNode).build();
        VarsNode varsNode = VarsNode.builder().var(varNode).vars(null).build();

        SumNode sumNode = SumNode.builder().identifier(identvar1).expression(booleanNode).build();

        InstrsNode instrsNode = InstrsNode.builder().instruction(sumNode).instrs(null).build();


        MainNode mainNode = MainNode.builder().vars(varsNode).instrs(instrsNode).build();

        typeChecker = new TypeCheckerImpl(mainNode);
        typeChecker.typeCheck();


    }





    @Test(expected = TypeCheckerException.class)
    public void SumNodeTypeCheker__Identifier__ArrayItem__Type_Identifier_NOT_INT() throws TypeCheckerException {

        IdentNode identvar1 = IdentNode.builder().value("i").build();

        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.BOOLEAN).build();
        NumberNode numberNode = NumberNode.builder().value(6).build();
        NumberNode numberNode2 = NumberNode.builder().value(2).build();

        ArrayNode arrayNode = ArrayNode.builder().typeMeth(typeMethNode).identifier(identvar1).expression(numberNode).build();
        ArrayItemNode arrayItemNode = ArrayItemNode.builder().identifier(identvar1).expression(numberNode2).build();

        VarsNode varsNode = VarsNode.builder().var(arrayNode).vars(null).build();

        SumNode sumNode = SumNode.builder().identifier(arrayItemNode).expression(numberNode).build();

        InstrsNode instrsNode = InstrsNode.builder().instruction(sumNode).instrs(null).build();

        MainNode mainNode = MainNode.builder().vars(varsNode).instrs(instrsNode).build();

        typeChecker = new TypeCheckerImpl(mainNode);
        typeChecker.typeCheck();

    }

    @Test(expected = TypeCheckerException.class)
    public void SumNodeTypeCheker__Identifier__ArrayItem__Type_Expression_NOT_INT() throws TypeCheckerException {

        IdentNode identvar1 = IdentNode.builder().value("i").build();

        TypeMethNode typeMethNode2 = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();
        BooleanNode booleanNode = BooleanNode.builder().value(Boolean.TRUE).build();
        NumberNode numberNode = NumberNode.builder().value(6).build();
        NumberNode numberNode2 = NumberNode.builder().value(2).build();

        ArrayNode arrayNode = ArrayNode.builder().typeMeth(typeMethNode2).identifier(identvar1).expression(numberNode).build();
        ArrayItemNode arrayItemNode = ArrayItemNode.builder().identifier(identvar1).expression(numberNode2).build();

        VarsNode varsNode = VarsNode.builder().var(arrayNode).vars(null).build();

        SumNode sumNode = SumNode.builder().identifier(arrayItemNode).expression(booleanNode).build();

        InstrsNode instrsNode = InstrsNode.builder().instruction(sumNode).instrs(null).build();

        MainNode mainNode = MainNode.builder().vars(varsNode).instrs(instrsNode).build();

        typeChecker = new TypeCheckerImpl(mainNode);
        typeChecker.typeCheck();

    }



}
