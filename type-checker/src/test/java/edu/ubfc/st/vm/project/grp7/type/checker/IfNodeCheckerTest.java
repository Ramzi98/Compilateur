package edu.ubfc.st.vm.project.grp7.type.checker;

import edu.ubfc.st.vm.project.grp7.memory.SymbolDictionnary;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class IfNodeCheckerTest {
    TypeChecker typeChecker;
    SymbolDictionnary symbolDictionnary;
    TypeCheckerVisitor typeCheckerVisitor;

    @Before
    public void start(){
        typeCheckerVisitor = new TypeCheckerVisitor();
        symbolDictionnary = new SymbolDictionnary();
    }

    @Test
    public void IfNodeTypeCheker__First__Second__Pass() throws TypeCheckerException {

        NumberNode numberNode1 = NumberNode.builder().value(1).build();
        NumberNode numberNode3 = NumberNode.builder().value(3).build();

        EqualsNode equalsNode = EqualsNode.builder().leftOperand(numberNode3).rightOperand(numberNode1).build();


        IdentNode identvar1 = IdentNode.builder().value("i").build();

        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();
        NumberNode numberNode = NumberNode.builder().value(2).build();

        VarNode varNode = VarNode.builder().typeMeth(typeMethNode).identifier(identvar1).expression(numberNode).build();
        VarsNode varsNode = VarsNode.builder().var(varNode).vars(null).build();

        IncrementNode incrementNode = IncrementNode.builder().identifier(identvar1).build();

        InstrsNode instrsNode = InstrsNode.builder().instruction(incrementNode).instrs(null).build();

        IfNode ifNode = IfNode.builder().expression(equalsNode).trueInstrs(instrsNode).falseInstrs(instrsNode).build();

        InstrsNode instrsNode2 = InstrsNode.builder().instruction(ifNode).instrs(null).build();

        MainNode mainNode = MainNode.builder().vars(varsNode).instrs(instrsNode2).build();

        typeChecker = new TypeCheckerImpl(mainNode);
        typeChecker.typeCheck();

        assertThat(symbolDictionnary.find(identvar1.value()),is(-1));

    }

    @Test(expected = TypeCheckerException.class)
    public void IfNodeTypeCheker__Exception__In__Condition() throws TypeCheckerException {

        IdentNode identvar1 = IdentNode.builder().value("i").build();

        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();
        NumberNode numberNode = NumberNode.builder().value(2).build();

        VarNode varNode = VarNode.builder().typeMeth(typeMethNode).identifier(identvar1).expression(numberNode).build();
        VarsNode varsNode = VarsNode.builder().var(varNode).vars(null).build();

        IncrementNode incrementNode = IncrementNode.builder().identifier(identvar1).build();

        InstrsNode instrsNode = InstrsNode.builder().instruction(incrementNode).instrs(null).build();

        IfNode ifNode = IfNode.builder().expression(incrementNode).trueInstrs(instrsNode).falseInstrs(instrsNode).build();

        InstrsNode instrsNode2 = InstrsNode.builder().instruction(ifNode).instrs(null).build();

        MainNode mainNode = MainNode.builder().vars(varsNode).instrs(instrsNode2).build();

        typeChecker = new TypeCheckerImpl(mainNode);
        typeChecker.typeCheck();

    }



}
