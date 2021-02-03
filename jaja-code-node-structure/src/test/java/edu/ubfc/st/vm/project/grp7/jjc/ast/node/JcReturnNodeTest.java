package edu.ubfc.st.vm.project.grp7.jjc.ast.node;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JcReturnNodeTest {
    @Before
    public void setup() {
        jcReturnNode = JcReturnNode.builder()
                .line(30)
                .column(15)
                .next(jajaCodeNode)
                .build();
    }

    private JcReturnNode jcReturnNode;
    private JcIfNode jajaCodeNode;

    @Test
    public void check__JcReturnNode__Line() {
        MatcherAssert.assertThat(jcReturnNode.line(), is(30));
    }

    @Test
    public void check__JcReturnNode__Column() {
        MatcherAssert.assertThat(jcReturnNode.column(), is(15));
    }

    @Test
    public void check__JcReturnNode__next() {
        assertThat(jcReturnNode.next(), Matchers.is(jajaCodeNode));
    }

    @Test
    public void check__JcReturnNode__ChildrenMethod__FirstChild() {
        MatcherAssert.assertThat(jcReturnNode.children(0) , Matchers.is(jajaCodeNode));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__AndNode__ChildrenMethod__OtherChild() {
        jcReturnNode.children(2);
    }
}
