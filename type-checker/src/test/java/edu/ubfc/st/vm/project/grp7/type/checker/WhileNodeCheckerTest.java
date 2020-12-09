package edu.ubfc.st.vm.project.grp7.type.checker;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.ast.IllFormedNodeException;
import edu.ubfc.st.vm.project.grp7.memory.SymbolDictionnary;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class WhileNodeCheckerTest {
    TypeChecker typeChecker;
    SymbolDictionnary symbolDictionnary;
    TypeCheckerVisitor typeCheckerVisitor;

    @Before
    public void start(){
        typeCheckerVisitor = new TypeCheckerVisitor();
        symbolDictionnary = new SymbolDictionnary();
    }

    @Test
    public void WhileNodeTypeCheker__First__Second__Pass() throws IllFormedNodeException {

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

        WhileNode whileNode = WhileNode.builder().expression(equalsNode).instrs(instrsNode).build();

        InstrsNode instrsNode2 = InstrsNode.builder().instruction(whileNode).instrs(null).build();

        MainNode mainNode = MainNode.builder().vars(varsNode).instrs(instrsNode2).build();

        typeChecker = new TypeChecker(mainNode);
        typeChecker.setsymbolDictionnary(symbolDictionnary);
        typeChecker.typeCheck();

        assertThat(symbolDictionnary.find(identvar1.value()),is(-1));

    }

    @Test(expected = IllFormedNodeException.class)
    public void WhileNodeTypeCheker__Exception__In__Condition() throws IllFormedNodeException {


        IdentNode identvar1 = IdentNode.builder().value("i").build();

        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();
        NumberNode numberNode = NumberNode.builder().value(2).build();

        VarNode varNode = VarNode.builder().typeMeth(typeMethNode).identifier(identvar1).expression(numberNode).build();
        VarsNode varsNode = VarsNode.builder().var(varNode).vars(null).build();

        IncrementNode incrementNode = IncrementNode.builder().identifier(identvar1).build();

        InstrsNode instrsNode = InstrsNode.builder().instruction(incrementNode).instrs(null).build();

        WhileNode whileNode = WhileNode.builder().expression(incrementNode).instrs(instrsNode).build();

        InstrsNode instrsNode2 = InstrsNode.builder().instruction(whileNode).instrs(null).build();

        MainNode mainNode = MainNode.builder().vars(varsNode).instrs(instrsNode2).build();

        typeChecker = new TypeChecker(mainNode);
        typeChecker.setsymbolDictionnary(symbolDictionnary);
        typeChecker.typeCheck();
    }


}
