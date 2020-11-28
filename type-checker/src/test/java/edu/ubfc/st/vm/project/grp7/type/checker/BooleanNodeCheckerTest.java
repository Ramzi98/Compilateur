package edu.ubfc.st.vm.project.grp7.type.checker;

import edu.ubfc.st.vm.project.grp7.ast.IllFormedNodeException;
import edu.ubfc.st.vm.project.grp7.memory.SORTE;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.BooleanNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.NumberNode;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BooleanNodeCheckerTest {

    TypeCheckerVisitor typeChecker;
    @Before
    public void start(){

        typeChecker = new TypeCheckerVisitor();

    }

    @Test
    public void BooleanNodeTypeCheck__withTrue() throws IOException, IllFormedNodeException {

        BooleanNode node = BooleanNode.builder().value(true).build();
        typeChecker.visit(node);
        assertThat(typeChecker.miniJajaNodeType.get(node),is(SORTE.BOOLEAN));
    }

    @Test
    public void BooleanNodeTypeCheck__withFalse() throws IOException, IllFormedNodeException {

        BooleanNode node = BooleanNode.builder().value(false).build();
        typeChecker.visit(node);
        assertThat(typeChecker.miniJajaNodeType.get(node),is(SORTE.BOOLEAN));
    }

    @Test(expected = IllFormedNodeException.class)
    public void BooleanNodeTypeCheck__withException() throws IOException, IllFormedNodeException {

        BooleanNode node = BooleanNode.builder().build();
        typeChecker.visit(node);

    }
}
