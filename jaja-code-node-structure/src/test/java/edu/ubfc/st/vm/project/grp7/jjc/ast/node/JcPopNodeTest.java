package edu.ubfc.st.vm.project.grp7.jjc.ast.node;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JcPopNodeTest {
    @Before
    public void setup() {
        jcPopNode = JcPopNode.builder()
                .line(30)
                .column(15)
                .next(jajaCodeNode)
                .build();
    }

    private JcPopNode jcPopNode;
    private JcGotoNode jajaCodeNode;

    @Test
    public void check__JcPopNode__Line() {
        MatcherAssert.assertThat(jcPopNode.line(), is(30));
    }

    @Test
    public void check__JcPopNode__Column() {
        MatcherAssert.assertThat(jcPopNode.column(), is(15));
    }

    @Test
    public void check__JcPopNode__next() {
        assertThat(jcPopNode.next(), Matchers.is(jajaCodeNode));
    }

    @Test
    public void check__JcPopNode__ChildrenMethod__FirstChild() {
        MatcherAssert.assertThat(jcPopNode.children(0) , Matchers.is(jajaCodeNode));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__AndNode__ChildrenMethod__OtherChild() {
        jcPopNode.children(2);
    }
}
