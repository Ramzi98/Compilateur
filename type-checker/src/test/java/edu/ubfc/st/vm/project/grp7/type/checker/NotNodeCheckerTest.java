package edu.ubfc.st.vm.project.grp7.type.checker;

import edu.ubfc.st.vm.project.grp7.ast.IllFormedNodeException;
import edu.ubfc.st.vm.project.grp7.memory.SORTE;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class NotNodeCheckerTest {

    TypeCheckerVisitor typeChecker;
    @Before
    public void start(){

        typeChecker = new TypeCheckerVisitor();

    }

    @Test
    public void NotNodeTypeCheck() throws IOException, IllFormedNodeException {


        BooleanNode expression = BooleanNode.builder().value(true).build() ;
        NotNode node = NotNode.builder().expression(expression).build();
        typeChecker.visit(node);
        assertThat(typeChecker.miniJajaNodeType.get(node),is(SORTE.BOOLEAN));
    }

    @Test(expected = IllFormedNodeException.class)
    public void NotNodeTypeCheck__withException() throws IOException, IllFormedNodeException {

        BooleanNode expression = BooleanNode.builder().build() ;
        NotNode node = NotNode.builder().expression(expression).build();
        typeChecker.visit(node);
        assertThat(typeChecker.miniJajaNodeType.get(node),is(SORTE.BOOLEAN));

    }

    @Test(expected = IllFormedNodeException.class)
    public void NotNodeTypeCheck__WrongType__withException() throws IOException, IllFormedNodeException {

        NumberNode expression = NumberNode.builder().value(5).build() ;
        NotNode node = NotNode.builder().expression(expression).build();
        typeChecker.visit(node);
        assertThat(typeChecker.miniJajaNodeType.get(node),is(SORTE.BOOLEAN));

    }
}
