package edu.ubfc.st.vm.project.grp7.jjc.ast.node;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JcIfNodeTest {
    @Before
    public void setup() {
        jcIfNode = JcIfNode.builder()
                .line(30)
                .column(15)
                .next(jajaCodeNode)
                .adresse(100)
                .build();

        jcIfNode.setAdresse(2);
    }

    private JcIfNode jcIfNode;
    private JcPopNode jajaCodeNode;

    @Test
    public void check__JcIfNode__Line() {
        assertThat(jcIfNode.line(), is(30));
    }

    @Test
    public void check__JcIfNode__Column() {
        assertThat(jcIfNode.column(), is(15));
    }

    @Test
    public void check__JcIfNode__next() {
        assertThat(jcIfNode.next(), is(jajaCodeNode));
    }

    @Test
    public void check__JcIfNode__adresse() {
        assertThat(jcIfNode.adresse(), is(2));
    }

    @Test
    public void check__JcIfNode__ChildrenMethod__FirstChild() {
        assertThat(jcIfNode.children(0) , is(jajaCodeNode));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__AndNode__ChildrenMethod__OtherChild() {
        jcIfNode.children(2);
    }
}
