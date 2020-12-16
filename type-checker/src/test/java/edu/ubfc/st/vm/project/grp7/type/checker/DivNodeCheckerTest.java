package edu.ubfc.st.vm.project.grp7.type.checker;

import edu.ubfc.st.vm.project.grp7.ast.IllFormedNodeException;
import edu.ubfc.st.vm.project.grp7.memory.SORTE;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.BooleanNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.DivNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.NumberNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.PlusNode;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DivNodeCheckerTest {

    TypeCheckerVisitor typeChecker;
    @Before
    public void start(){

        typeChecker = new TypeCheckerVisitor();

    }

    @Test
    public void DivNodeTypeCheck() throws IOException, TypeCheckerException, IllFormedNodeException {


        NumberNode leftOperand = NumberNode.builder().value(5).build() ;
        NumberNode rightOperand = NumberNode.builder().value(6).build();
        DivNode node = DivNode.builder().leftOperand(leftOperand).rightOperand(rightOperand).build();
        typeChecker.visit(node);
        assertThat(typeChecker.miniJajaNodeType.get(node),is(SORTE.INT));
    }


    @Test(expected = TypeCheckerException.class)
    public void DivNodeTypeCheck__withException() throws IOException, TypeCheckerException, IllFormedNodeException {

        NumberNode leftOperand = NumberNode.builder().build();
        NumberNode rightOperand = NumberNode.builder().value(6).build();
        DivNode node = DivNode.builder().leftOperand(leftOperand).rightOperand(rightOperand).build();
        typeChecker.visit(node);

    }

    @Test(expected = TypeCheckerException.class)
    public void DivNodeTypeCheck__LeftOperandInvalid__withException() throws IOException, TypeCheckerException, IllFormedNodeException {

        BooleanNode leftOperand = BooleanNode.builder().value(true).build();
        NumberNode rightOperand = NumberNode.builder().value(6).build();
        DivNode node = DivNode.builder().leftOperand(leftOperand).rightOperand(rightOperand).build();
        typeChecker.visit(node);

    }
    @Test(expected = TypeCheckerException.class)
    public void PlusNodeTypeCheck__RightOperandInvalid__withException() throws IOException, TypeCheckerException, IllFormedNodeException {

        NumberNode leftOperand = NumberNode.builder().value(6).build();
        BooleanNode rightOperand = BooleanNode.builder().value(true).build();
        DivNode node = DivNode.builder().leftOperand(leftOperand).rightOperand(rightOperand).build();
        typeChecker.visit(node);

    }
}
