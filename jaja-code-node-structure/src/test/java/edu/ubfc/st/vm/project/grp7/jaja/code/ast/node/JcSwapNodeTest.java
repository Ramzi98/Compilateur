package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JcSwapNodeTest {
    @Before
    public void setup() {
        jcSwapNode = JcSwapNode.builder()
                .line(30)
                .column(15)
                .next(jajaCodeNode)
                .build();
    }

    private JcSwapNode jcSwapNode;
    private JcIfNode jajaCodeNode;


    @Test
    public void check__JcSwapNode__Line() {
        assertThat(jcSwapNode.line(), is(30));
    }

    @Test
    public void check__JcSwapNode__Column() {
        assertThat(jcSwapNode.column(), is(15));
    }

    @Test
    public void check__JcSwapNode__next() {
        assertThat(jcSwapNode.next(), is(jajaCodeNode));
    }

    @Test
    public void check__JcSwapNode__ChildrenMethod__FirstChild() {
        assertThat(jcSwapNode.children(0), is(jajaCodeNode));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__JcSwapNode__ChildrenMethod__OtherChild() {
        jcSwapNode.children(2);
    }
}
