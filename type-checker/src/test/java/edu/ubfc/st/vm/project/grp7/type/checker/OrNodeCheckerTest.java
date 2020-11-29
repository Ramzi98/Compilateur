package edu.ubfc.st.vm.project.grp7.type.checker;

import edu.ubfc.st.vm.project.grp7.ast.IllFormedNodeException;
import edu.ubfc.st.vm.project.grp7.memory.SORTE;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.AndNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.BooleanNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.OrNode;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class OrNodeCheckerTest {

    TypeCheckerVisitor typeChecker;
    @Before
    public void start(){

        typeChecker = new TypeCheckerVisitor();

    }

    @Test
    public void OrNodeTypeCheck() throws IOException, IllFormedNodeException {


        BooleanNode leftOperand = BooleanNode.builder().value(true).build() ;
        BooleanNode rightOperand = BooleanNode.builder().value(false).build();
        OrNode node = OrNode.builder().leftOperand(leftOperand).rightOperand(rightOperand).build();
        typeChecker.visit(node);
        assertThat(typeChecker.miniJajaNodeType.get(node),is(SORTE.BOOLEAN));
    }


    @Test(expected = IllFormedNodeException.class)
    public void OrNodeTypeCheck__withException() throws IOException, IllFormedNodeException {

        BooleanNode leftOperand = BooleanNode.builder().value(true).build() ;
        BooleanNode rightOperand = BooleanNode.builder().build();
        OrNode node = OrNode.builder().leftOperand(leftOperand).rightOperand(rightOperand).build();
        typeChecker.visit(node);

    }
}
