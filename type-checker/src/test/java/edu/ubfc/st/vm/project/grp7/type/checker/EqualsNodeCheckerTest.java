package edu.ubfc.st.vm.project.grp7.type.checker;

import edu.ubfc.st.vm.project.grp7.memory.SORTE;
import edu.ubfc.st.vm.project.grp7.mjj.ast.node.AndNode;
import edu.ubfc.st.vm.project.grp7.mjj.ast.node.BooleanNode;
import edu.ubfc.st.vm.project.grp7.mjj.ast.node.EqualsNode;
import edu.ubfc.st.vm.project.grp7.mjj.ast.node.NumberNode;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class EqualsNodeCheckerTest {

    TypeCheckerVisitor typeChecker;
    @Before
    public void start(){

        typeChecker = new TypeCheckerVisitor();

    }

    @Test
    public void EqualsNodeTypeCheck() throws IOException, TypeCheckerException {


        BooleanNode leftOperand = BooleanNode.builder().value(true).build() ;
        BooleanNode rightOperand = BooleanNode.builder().value(true).build();
        EqualsNode node = EqualsNode.builder().leftOperand(leftOperand).rightOperand(rightOperand).build();
        typeChecker.visit(node);
        assertThat(typeChecker.miniJajaNodeType.get(node),is(SORTE.BOOLEAN));
    }


    @Test(expected = TypeCheckerException.class)
    public void EqualsNodeTypeCheck__withException() throws IOException, TypeCheckerException {

        BooleanNode leftOperand = BooleanNode.builder().value(true).build() ;
        NumberNode rightOperand = NumberNode.builder().value(5).build();
        AndNode node = AndNode.builder().leftOperand(leftOperand).rightOperand(rightOperand).build();
        typeChecker.visit(node);

    }

    @Test(expected = TypeCheckerException.class)
    public void EqualsNodeTypeCheck__RightOperandInvalid__withException() throws IOException, TypeCheckerException {

        BooleanNode leftOperand = BooleanNode.builder().value(true).build();
        NumberNode rightOperand = NumberNode.builder().value(6).build();
        EqualsNode node = EqualsNode.builder().leftOperand(leftOperand).rightOperand(rightOperand).build();
        typeChecker.visit(node);

    }
}
