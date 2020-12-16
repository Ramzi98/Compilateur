package edu.ubfc.st.vm.project.grp7.type.checker;

import edu.ubfc.st.vm.project.grp7.ast.IllFormedNodeException;
import edu.ubfc.st.vm.project.grp7.memory.SORTE;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SubNodeCheckerTest {

    TypeCheckerVisitor typeChecker;
    @Before
    public void start(){

        typeChecker = new TypeCheckerVisitor();

    }

    @Test
    public void SubNodeTypeCheck() throws IOException, TypeCheckerException, IllFormedNodeException {


        NumberNode leftOperand = NumberNode.builder().value(5).build() ;
        NumberNode rightOperand = NumberNode.builder().value(6).build();
        SubNode node = SubNode.builder().leftOperand(leftOperand).rightOperand(rightOperand).build();
        typeChecker.visit(node);
        assertThat(typeChecker.miniJajaNodeType.get(node),is(SORTE.INT));
    }

    @Test(expected = TypeCheckerException.class)
    public void SubNodeTypeCheck__withException() throws IOException, TypeCheckerException, IllFormedNodeException {

        NumberNode leftOperand = NumberNode.builder().build();
        NumberNode rightOperand = NumberNode.builder().value(6).build();
        SubNode node = SubNode.builder().leftOperand(leftOperand).rightOperand(rightOperand).build();
        typeChecker.visit(node);

    }

    @Test(expected = TypeCheckerException.class)
    public void SubNodeTypeCheck__LeftOperandInvalid__withException() throws IOException, TypeCheckerException, IllFormedNodeException {

        BooleanNode leftOperand = BooleanNode.builder().value(true).build();
        NumberNode rightOperand = NumberNode.builder().value(6).build();
        SubNode node = SubNode.builder().leftOperand(leftOperand).rightOperand(rightOperand).build();
        typeChecker.visit(node);

    }
    @Test(expected = TypeCheckerException.class)
    public void SubNodeTypeCheck__RightOperandInvalid__withException() throws IOException, TypeCheckerException, IllFormedNodeException {

        NumberNode leftOperand = NumberNode.builder().value(6).build();
        BooleanNode rightOperand = BooleanNode.builder().value(true).build();
        SubNode node = SubNode.builder().leftOperand(leftOperand).rightOperand(rightOperand).build();
        typeChecker.visit(node);

    }
}
