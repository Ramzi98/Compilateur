package edu.ubfc.st.vm.project.grp7.jjc.ast.node;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JcAloadNodeTest {
    @Before
    public void setup() {
        jcAloadNode = JcAloadNode.builder()
                .line(30)
                .column(15)
                .next(jajaCodeNode)
                .identifier("ident")
                .build();
    }

    private JcAloadNode jcAloadNode;
    private JcPushNode jajaCodeNode;

    @Test
    public void check__JcAloadNode__Line() {
        assertThat(jcAloadNode.line(), is(30));
    }

    @Test
    public void check__JcAloadNode__Column() {
        assertThat(jcAloadNode.column(), is(15));
    }

    @Test
    public void check__JcAloadNode__next() {
        assertThat(jcAloadNode.next(), Matchers.is(jajaCodeNode));
    }

    @Test
    public void check__JcAloadNode__identifier() {
        assertThat(jcAloadNode.identifier(), is("ident"));
    }

    @Test
    public void check__JcAloadNode__ChildrenMethod__FirstChild() {
        assertThat(jcAloadNode.children(0) , Matchers.is(jajaCodeNode));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__AndNode__ChildrenMethod__OtherChild() {
        jcAloadNode.children(2);
    }
}
