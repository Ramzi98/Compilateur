package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JcNewarrayNodeTest {
    @Before
    public void setup() {
        jcNewarrayNode = JcNewarrayNode.builder()
                .line(30)
                .column(15)
                .next(jajaCodeNode)
                .type(JcNewarrayNode.Type.INT)
                .identifier("max")
                .build();

    }

    private JcNewarrayNode jcNewarrayNode;
    private JcGotoNode jajaCodeNode;


    @Test
    public void check__JcNewarrayNode__Line() {
        assertThat(jcNewarrayNode.line(), is(30));
    }

    @Test
    public void check__JcNewarrayNode__Column() {
        assertThat(jcNewarrayNode.column(), is(15));
    }

    @Test
    public void check__JcNewarrayNode__next() {
        assertThat(jcNewarrayNode.next(), is(jajaCodeNode));
    }

    @Test
    public void check__JcNewarrayNode__type() {
        assertThat(jcNewarrayNode.type(), is(JcNewarrayNode.Type.INT));
    }

    @Test
    public void check__JcNewarrayNode__identifier() {
        assertThat(jcNewarrayNode.identifier(), is("max"));
    }

    @Test
    public void check__JcNewarrayNode__ChildrenMethod__FirstChild() {
        assertThat(jcNewarrayNode.children(0), is(jajaCodeNode));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__JcNewarrayNode__ChildrenMethod__OtherChild() {
        jcNewarrayNode.children(2);
    }
}