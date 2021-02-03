package edu.ubfc.st.vm.project.grp7.jjc.ast.node;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JcAstoreNodeTest {
    @Before
    public void setup() {
        jcAstoreNode = JcAstoreNode.builder()
                .line(30)
                .column(15)
                .next(jajaCodeNode)
                .identifier("ident")
                .build();
    }

    private JcAstoreNode jcAstoreNode;
    private JcPushNode jajaCodeNode;

    @Test
    public void check__JcAstoreNode__Line() {
        assertThat(jcAstoreNode.line(), is(30));
    }

    @Test
    public void check__JcAstoreNode__Column() {
        assertThat(jcAstoreNode.column(), is(15));
    }

    @Test
    public void check__JcAstoreNode__next() {
        assertThat(jcAstoreNode.next(), Matchers.is(jajaCodeNode));
    }

    @Test
    public void check__JcAstoreNode__identifier() {
        assertThat(jcAstoreNode.identifier(), is("ident"));
    }

    @Test
    public void check__JcAstoreNode__ChildrenMethod__FirstChild() {
        assertThat(jcAstoreNode.children(0) , Matchers.is(jajaCodeNode));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__AndNode__ChildrenMethod__OtherChild() {
        jcAstoreNode.children(2);
    }
}
