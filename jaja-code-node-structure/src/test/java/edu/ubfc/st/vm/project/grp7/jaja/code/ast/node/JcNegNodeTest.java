package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JcNegNodeTest {

    @Before
    public void setup() {
        jcNegNode = JcNegNode.builder()
                .line(30)
                .column(15)
                .next(jajaCodeNode)
                .build();
    }

    private JcNegNode jcNegNode;
    private JajaCodeNode jajaCodeNode;

    @Test
    public void check__JcNegNode__Line() {
        assertThat(jcNegNode.line(), is(30));
    }

    @Test
    public void check__JcNegNode__Column() {
        assertThat(jcNegNode.column(), is(15));
    }

    @Test
    public void check__JcNegNode__next() {
        assertThat(jcNegNode.next(), is(jajaCodeNode));
    }

    @Test
    public void check__JcNegNode__ChildrenMethod__FirstChild() {
        assertThat(jcNegNode.children(0) , is(jajaCodeNode));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__JcNegNode__ChildrenMethod__OtherChild() {
        jcNegNode.children(2);
    }
}
