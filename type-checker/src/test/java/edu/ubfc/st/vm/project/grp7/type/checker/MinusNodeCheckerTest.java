package edu.ubfc.st.vm.project.grp7.type.checker;

import edu.ubfc.st.vm.project.grp7.memory.SORTE;
import edu.ubfc.st.vm.project.grp7.mjj.ast.node.BooleanNode;
import edu.ubfc.st.vm.project.grp7.mjj.ast.node.MinusNode;
import edu.ubfc.st.vm.project.grp7.mjj.ast.node.NumberNode;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MinusNodeCheckerTest {

    TypeCheckerVisitor typeChecker;
    @Before
    public void start(){

        typeChecker = new TypeCheckerVisitor();

    }

    @Test
    public void MinusNodeTypeCheck() throws IOException, TypeCheckerException {


        NumberNode number = NumberNode.builder().value(5).build() ;
        MinusNode node = MinusNode.builder().expression(number).build();
        typeChecker.visit(node);
        assertThat(typeChecker.miniJajaNodeType.get(node),is(SORTE.INT));
    }

    @Test(expected = TypeCheckerException.class)
    public void MinusNodeTypeCheck__withException() throws IOException, TypeCheckerException {

        NumberNode number = NumberNode.builder().build();
        MinusNode node = MinusNode.builder().expression(number).build();
        typeChecker.visit(node);

    }

    @Test(expected = TypeCheckerException.class)
    public void MinusNodeTypeCheck__WrongType__withException() throws IOException, TypeCheckerException {

        BooleanNode number = BooleanNode.builder().value(true).build();
        MinusNode node = MinusNode.builder().expression(number).build();
        typeChecker.visit(node);

    }
}
