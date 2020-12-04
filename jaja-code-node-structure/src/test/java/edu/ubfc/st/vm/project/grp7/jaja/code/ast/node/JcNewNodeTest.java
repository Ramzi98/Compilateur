package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JcNewNodeTest {
    @Before
    public void setup() {
        jcNewNode = JcNewNode.builder()
                .line(72)
                .column(67)
                .depth(100)
                .next(jajaCodeNode)
                .sorte(JcNewNode.Sorte.VAR)
                .type(JcNewNode.Type.INT)
                .identifier("max")
                .build();

    }

    private JcNewNode jcNewNode;
    private JcGotoNode jajaCodeNode;


    @Test
    public void check__JcNewNode__Line() {
        assertThat(jcNewNode.line(), is(72));
    }

    @Test
    public void check__JcNewNode__Column() {
        assertThat(jcNewNode.column(), is(67));
    }

    @Test
    public void check__JcNewNode__adr() {
        assertThat(jcNewNode.depth(), is(100));
    }

    @Test
    public void check__JcNewNode__next() {
        assertThat(jcNewNode.next(), is(jajaCodeNode));
    }

    @Test
    public void check__JcNewNode__sorte() {
        assertThat(jcNewNode.sorte(), is(JcNewNode.Sorte.VAR));
    }

    @Test
    public void check__JcNewNode__type() {
        assertThat(jcNewNode.type(), is(JcNewNode.Type.INT));
    }

    @Test
    public void check__JcNewNode__identifier() {
        assertThat(jcNewNode.identifier(), is("max"));
    }

    @Test
    public void check__JcNewNode__ChildrenMethod__FirstChild() {
        assertThat(jcNewNode.children(0), is(jajaCodeNode));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__JcNewNode__ChildrenMethod__OtherChild() {
        jcNewNode.children(2);
    }
}
