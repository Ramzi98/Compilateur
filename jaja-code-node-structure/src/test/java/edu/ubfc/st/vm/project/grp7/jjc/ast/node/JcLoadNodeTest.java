package edu.ubfc.st.vm.project.grp7.jjc.ast.node;

import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JcLoadNodeTest {
    @Before
    public void setup() {
        jcLoadNode = JcLoadNode.builder()
                .line(30)
                .column(15)
                .next(jajaCodeNode)
                .identifier("ident")
                .build();
    }

    private JcLoadNode jcLoadNode;
    private JcReturnNode jajaCodeNode;

    @Test
    public void check__JcLoadNode__Line() {
        MatcherAssert.assertThat(jcLoadNode.line(), is(30));
    }

    @Test
    public void check__JcLoadNode__Column() {
        MatcherAssert.assertThat(jcLoadNode.column(), is(15));
    }

    @Test
    public void check__JcLoadNode__next() {
        assertThat(jcLoadNode.next(), is(jajaCodeNode));
    }

    @Test
    public void check__JcLoadNode__identifier() {
        assertThat(jcLoadNode.identifier(), is("ident"));
    }

    @Test
    public void check__JcLoadNode__ChildrenMethod__FirstChild() {
        MatcherAssert.assertThat(jcLoadNode.children(0) , is(jajaCodeNode));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__AndNode__ChildrenMethod__OtherChild() {
        jcLoadNode.children(2);
    }
}
