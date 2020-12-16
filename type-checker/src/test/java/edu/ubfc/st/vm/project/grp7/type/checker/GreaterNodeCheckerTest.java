package edu.ubfc.st.vm.project.grp7.type.checker;

import edu.ubfc.st.vm.project.grp7.memory.SORTE;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class GreaterNodeCheckerTest {

    TypeCheckerVisitor typeChecker;
    @Before
    public void start(){

        typeChecker = new TypeCheckerVisitor();

    }

    @Test
    public void GreaterNodeTypeCheck() throws IOException, TypeCheckerException {


        NumberNode leftOperand = NumberNode.builder().value(5).build() ;
        NumberNode rightOperand = NumberNode.builder().value(6).build();
        GreaterNode node = GreaterNode.builder().leftOperand(leftOperand).rightOperand(rightOperand).build();
        typeChecker.visit(node);
        assertThat(typeChecker.miniJajaNodeType.get(node),is(SORTE.BOOLEAN));
    }


    @Test(expected = TypeCheckerException.class)
    public void GreaterNodeTypeCheck__withException() throws IOException, TypeCheckerException {

        NumberNode leftOperand = NumberNode.builder().build();
        NumberNode rightOperand = NumberNode.builder().value(6).build();
        GreaterNode node = GreaterNode.builder().leftOperand(leftOperand).rightOperand(rightOperand).build();
        typeChecker.visit(node);

    }
    @Test(expected = TypeCheckerException.class)
    public void GreaterNodeTypeCheck__LeftOperandInvalid__withException() throws IOException, TypeCheckerException {

        BooleanNode rightOperand = BooleanNode.builder().value(true).build();
        NumberNode leftOperand = NumberNode.builder().value(6).build();
        GreaterNode node = GreaterNode.builder().leftOperand(leftOperand).rightOperand(rightOperand).build();
        typeChecker.visit(node);

    }
    @Test(expected = TypeCheckerException.class)
    public void GreaterNodeTypeCheck__RightOperandInvalid__withException() throws IOException, TypeCheckerException {

        BooleanNode leftOperand = BooleanNode.builder().value(true).build();
        NumberNode rightOperand = NumberNode.builder().value(6).build();
        GreaterNode node = GreaterNode.builder().leftOperand(leftOperand).rightOperand(rightOperand).build();
        typeChecker.visit(node);

    }
}
